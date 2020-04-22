 
import pandas as pd
import csv
import re

#Create basic_stats dataframe.
basic_stats = pd.read_csv('Individual_Stats_Basic.csv', usecols=['Player', 'GP', 'Goals', 'Total Assists', 'Total Points', 'PIM'])

#Create advanced_stats dataframe.
advanced_stats = pd.read_csv('Individual_Stats_Advanced.csv', usecols=['Player', 'xGF', 'xGA'])

#Create bios dataframe.
bios = pd.read_csv('Individual_Bios.csv', usecols=['Player', 'Position', 'Age'])

#Create the games dataframe.
games = pd.read_csv('Hurricanes-Games.csv', usecols=['Game', 'SF', 'xGF', 'xGA', 'HDSF', 'HDSA', 'PDO'])

#Create games2 dataframe to parse game info. Split column into dates and box score.
games2 = games['Game'].str.split(" - ", n = 1, expand = True)

#Add Date column to games dataframe.
games['DATE'] = games2[0]

#Split box score columns to extract goals and opponent.
bs = games2[1].str.split(", ", n = 1, expand = True)

#Create lists to make new columns for games dataframe.
gf = []
opp = [] 
ga = []

#Loop over each row and extract goals and opponent name to lists.
for i in range(len(bs)):
    #Case where Hurricanes goals are first.
    if "Hurricanes" in bs.iat[i, 0]:
        gf.append(re.search(r"\d", bs.iat[i, 0]).group())
        opp.append(re.search(r"\w+\D+", bs.iat[i, 1]).group().rstrip())
        ga.append(re.search(r"\d", bs.iat[i, 1]).group())
    #Case where opponent goals are first.
    else:
        gf.append(re.search(r"\d", bs.iat[i, 1]).group())
        opp.append(re.search(r"\w+\D+", bs.iat[i, 0]).group().rstrip())
        ga.append(re.search(r"\d", bs.iat[i, 0]).group())

#Convert goals lists to ints.
gf = [int(i) for i in gf]
ga = [int(i) for i in ga]

#Add list columns to games dataframe.
games['GF'] = gf
games['GA'] = ga
games['OPP'] = opp

#Remove Game column from games dataframe.
games.drop('Game', 1, inplace = True)

#Re-order games dataframe to match database table order.
games = games[['DATE', 'OPP', 'GF', 'GA', 'SF', 'xGF', 'xGA', 'HDSF', 'HDSA', 'PDO']]

#Round the games PDO column.
games['PDO'] = games['PDO'].round(3)

#Print games dataframe to check order
print(games)

#Merge bios and basic stats using Player as key.
tstats = pd.merge(bios, basic_stats, on='Player')

#Merge temporary stats (tstats) and advanced stats using Player as key.
stats = pd.merge(tstats, advanced_stats, on='Player')

#Dictionary used to map numbers to player names.
numbers = {
    'Sebastian Aho':20,
    'Ryan Dzingel':18,
    'Warren Foegele':13,
    'Jordan Martinook':48,
    'Brock McGinn':23,
    'Martin Necas':88,
    'Nino Niederreiter':21,
    'Jordan Staal':11,
    'Andrei Svechnikov':37,
    'Teuvo Teravainen':86,
    'Vincent Trocheck':16,
    'Justin Williams':14,
    'Joel Edmundson':6,
    'Haydn Fleury':4,
    'Jake Gardiner':51,
    'Dougie Hamilton':19,
    'Brett Pesce':22,
    'Brady Skjei':76,
    'Jaccob Slavin':74,
    'Trevor van Riemsdyk':57,
    'Morgan Geekie':43
        }

#Purge stats dataframe of players not in our numbers dictionary.
stats = stats[stats['Player'].isin(numbers.keys())]

#Rename stats columns.
stats.columns = ['NAME', 'POS', 'AGE', 'GP', 'G', 'A', 'PTS', 'PIM', 'xGF', 'xGA']

#Add NUM column to stats using numbers dictionary.
stats['NUM'] = stats['NAME'].map(numbers)

#Reorder stats column to match database table order.
stats = stats[['NAME', 'NUM', 'AGE', 'POS', 'GP', 'G', 'A', 'PTS', 'PIM', 'xGF', 'xGA']]

#Adjust Aho's dumb position value.
stats.at[22, 'POS'] = 'C'

#Print stats to make sure it matches expected output.
#print(stats)

#Export dataframes to csv files and quote non-numeric fields to make SQL insert easier.
games.to_csv('Games.csv', quotechar="'", quoting=csv.QUOTE_NONNUMERIC, index=False)
stats.to_csv('Players.csv', quotechar="'", quoting=csv.QUOTE_NONNUMERIC, index=False)

#Create SQL insertion queries using Games.csv file.
g = open('Games.csv', 'r')
#Don't read first line as it is just column names.
lines = g.readlines()[1:]
for game in lines:
    #Remove newline character from each line.
    game = game.rstrip('\n')
    #Insert line into pre-formatted query syntax.
    print("INSERT INTO Games values(" + game + ");")

#Close Games.csv
g.close()

#Create SQL insertion query using Players.csv file.
f = open('Players.csv', 'r')
#Don't read first line as it is just column names.
lines = f.readlines()[1:]
for player in lines:
    #Remove newline character from each line.
    player = player.rstrip('\n')
    #Insert line into pre-formatted query syntax.
    print("INSERT INTO Players values(" + player + ");")

#Close Players.csv file.
f.close()

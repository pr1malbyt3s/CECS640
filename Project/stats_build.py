import pandas as pd
import csv

#Create basic_stats dataframe.
basic_stats = pd.read_csv('Individual_Stats_Basic.csv', usecols=['Player', 'GP', 'Goals', 'Total Assists', 'Total Points', 'PIM'])

#Create advanced_stats dataframe.
advanced_stats = pd.read_csv('Individual_Stats_Advanced.csv', usecols=['Player', 'xGF', 'xGA'])

#Create bios dataframe.
bios = pd.read_csv('Individual_Bios.csv', usecols=['Player', 'Position', 'Age'])

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
print(stats)

#Export to csv and quote non-numeric fields to make SQL insert easier.
stats.to_csv('Players.csv', quotechar="'", quoting=csv.QUOTE_NONNUMERIC, index=False)

#Create SQL insertion query using Players.csv file.
f = open('Players.csv', 'r')
#Don't read first line as it is just column values.
lines = f.readlines()[1:]
for player in lines:
    #Remove newline character from each line.
    player = player.rstrip('\n')
    #Insert line into pre-formatted query syntax.
    print("INSERT INTO Players values(" + player + ");")

#Close Players.csv file.
f.close()

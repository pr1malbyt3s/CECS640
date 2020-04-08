import pandas as pd

basic_stats = pd.read_csv('Individual_Stats_Basic.csv', sep=',')
                 #names=['Name', 'Position', 'GP', 'Goals', 'Total Assists', 'Total Points', 'PIM'])
#print(basic_stats['Name', 'Position', 'GP', 'Goals', 'Total Assists', 'Total Points', 'PIM'])
#basic_stats.columns = basic_stats.columns.to_string
print(basic_stats.columns.str.decode('utf-8'))

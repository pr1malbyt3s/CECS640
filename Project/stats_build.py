import pandas as pd

basic_stats = pd.read_csv('Individual_Stats_Basic.csv', index_col='Player', usecols=['Player', 'Position', 'GP', 'Goals', 'Total Assists', 'Total Points', 'PIM'])
print(basic_stats)


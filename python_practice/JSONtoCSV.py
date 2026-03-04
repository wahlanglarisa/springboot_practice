import pandas as pd

with open('C:\\Users\\wahla\\Downloads\\world-countries\\subdivisions\\subdivisions.json', encoding='utf-8-sig') as f_input:
    df = pd.read_json(f_input)

df.to_csv('state_res.csv', encoding='utf-8-sig', index=False)
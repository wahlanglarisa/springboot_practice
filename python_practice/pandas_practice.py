import pandas as pd
df=pd.read_excel("F:\\springboot_practice\\python_practice\\Employees.xlsx")
print(df.groupby(["Gender"]).mean(numeric_only=True).sort_values(by='Overtime Hours',ascending=False))
print(df[["First Name","Last Name"]])
print(df.dropna())
print(df.describe())
print(df.pivot_table(values="Annual Salary",index='Department',columns='Country',aggfunc='mean'))
df["Start Year"]=df["Start Date"].dt.year
print(df["Start Year"])
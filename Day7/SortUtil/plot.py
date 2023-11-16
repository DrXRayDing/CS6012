import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# List of file paths
file_paths = ['mergesort_AverageCase_Thr50.tsv', 'quicksort_RandomElement_AverageCase_Thr50.tsv', 'mergesort_BestCase_Thr50.tsv', 'quicksort_RandomElement_BestCase_Thr50.tsv', 'mergesort_WorstCase_Thr50.tsv', 'quicksort_RandomElement_WorstCase_Thr50.tsv']

# Create a figure
plt.figure(figsize=(10, 6))

# Loop through each file and plot the data
for file_path in file_paths:
    # Load the data from the TSV file
    data = pd.read_csv(file_path, header=None, sep='\t')  # No header in the file

    # Assign appropriate column names
    data.columns = ['Size', 'Average Time']

    # Plot the data
    plt.plot(data['Size'], data['Average Time'], marker='o', linestyle='-', label=file_path)

# Set the title, labels, and legend of the plot
plt.title('mergesort vs quicksort for three cases')
plt.xlabel('Size')
plt.ylabel('Average Time (ns)')
plt.legend()

# Show grid
plt.grid(True, which="both", ls="--")

# Save the figure
plt.savefig('merge_vs_quick_threeCases.png')

# Show the plot
#plt.show()

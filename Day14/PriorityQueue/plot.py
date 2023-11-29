import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# List of file paths
file_paths = ['Heap_In_Order_Remove.tsv', 'Heap_Permuted_Remove.tsv', 'TreeSet_In_Order_Remove.tsv', 'TreeSet_Permuted_Remove.tsv',]

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
plt.title('TreeSetPQ vs HeapPQ for removeMin')
plt.xlabel('Size')
plt.ylabel('Average Time (ns)')
plt.legend()

# Show grid
plt.grid(True, which="both", ls="--")

# Save the figure
plt.savefig('removeMin.png')

# Show the plot
#plt.show()

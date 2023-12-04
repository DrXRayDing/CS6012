package assignment07;

public class MediocreHashFunctor implements HashFunctor {
    @Override
    public int hash(String item) {

        int hash = 0; // Initialize the hash value to 0.

        // Iterate through each character of the string.
        for (int i = 0; i < item.length(); i++) {
            // Calculate the hash by summing up the products of each character's ASCII value
            // and the square of its position in the string (i + 1 ensures position starts from 1, not 0).
            // This incorporates both the character's value and its position in the string,
            // offering a basic level of complexity in the hash calculation.
            hash += ((int) item.charAt(i)) * Math.pow((i + 1), 2);
        }

        // Return the computed hash value.
        return hash;
    }
}

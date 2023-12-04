package assignment07;

public class GoodHashFunctor implements HashFunctor {

    @Override
    public int hash(String item) {
        // Initialize hash as an integer starting from a small prime number.
        int hash = 7;

        // Iterate through each character of the string.
        for (int i = 0; i < item.length(); i++) {
            // Multiply the current hash by a prime and add the character's ASCII value.
            // This creates a polynomial rolling hash
            hash = hash * 31 + item.charAt(i);
        }

        // Return the final hash value.
        // The use of a prime number reduces the likelihood of collisions.
        return hash;
    }
}

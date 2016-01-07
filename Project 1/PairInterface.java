
public interface PairInterface<T1,T2>
{
	/* Gets the first element of this pair.
	 * @return the first element of this pair.
	 */
	public T1 fst();

	/* Gets the second element of this pair.
	 * @return the second element of this pair.
	 */
	public T2 snd();
	
	/* Gets a NEW pair the represents the reversed order
	 * of this pair. For example, the reverse order of the
	 * pair (x,y) is (y,x).
	 * @return a NEW pair in reverse order
	 */	
	public PairInterface<T2,T1> reverse();
	
	/* Checks whether two pairs are equal. Note that the pair
	 * (a,b) is equal to the pair (x,y) if and only if a is
	 * equal to x and b is equal to y.
	 * 
	 * Note that if you forget to implement this method, your
	 * compiler will not complain since your class inherits this
	 * method from the class Object.
	 * 
	 * @param otherObject the object to be compared to this object.
	 * @return true if this pair is equal to aPair. Otherwise
	 * return false.
	 */	
	public boolean equals(Object otherObject);
	
	/* Generates a string representing this pair. Note that
	 * the String representing the pair (x,y) is "(x,y)". There
	 * is no whitespace unless x or y or both contain whitespace
	 * themselves.
	 * 
	 * Note that if you forget to implement this method, your
	 * compiler will not complain since your class inherits this
	 * method from the class Object.
	 * 
	 * @return a string representing this pair.
	 */	
	public String toString();
}
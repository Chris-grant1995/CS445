/**
 * It is okay to use ArrayList class but you are not allowed to use any other
 * predefined class supplied by Java.
 */
public class CompressDecompress
{
    static int global=0;
	/**
	 * Get a string representing a Huffman tree where its root node is root
	 * @param root the root node of a Huffman tree
	 * @return a string representing a Huffman tree
	 */
	public static String getTreeString(final BinaryNodeInterface<Character> root)
	{
		// TO DO
		String treeString = "";
		if(root==null)
			return treeString;
		if(root.isLeaf()) {
			treeString += "L";
			treeString += root.getData();
		}
		else{
			treeString+="I";
            if(root.getData()!=null)
                treeString+=root.getData();
            treeString+=getTreeString(root.getLeftChild());
            treeString+=getTreeString(root.getRightChild());
		}

		return treeString;	// Do not forget to change this line!!!
	}

	/**
	 * Compress the message using Huffman tree represented by treeString
	 * @param //treeString the string represents the Huffman tree of the message
	 * @param message the message to be compressed
	 * @return a string representing compressed message.
	 */
	public static String compress(final BinaryNodeInterface<Character> root, final String message)
	{
		// TO DO
        //HuffmanTree tree = new HuffmanTree(message);
        //char[] c = getTreeString(root).toCharArray();
        String mess = message.toLowerCase();
        System.out.println(mess);
        char[] tree = getTreeString(root).toCharArray();
        char[] m = mess.toCharArray();
        //System.out.println(c.length);
        String compressedMessage="";
        for(int i =0; i<m.length; i++)
        {
            for(int x =0; x<tree.length; x++)
            {
                if(!(Character.toString(tree[x]).equals("L") ||Character.toString(tree[x]).equals("I")) && m[i]==tree[x] ){
                    compressedMessage+=getPathTo(root,m[i]);
                }
            }
        }

        //System.out.println(c.length);


		return compressedMessage;	// Do not forget to change this line!!!
	}
    private static String dfs2(final BinaryNodeInterface<Character> root,String result,String path,char c)
    {
        if(root==null)
            return result;
        if(!root.isLeaf())
        {
            result=dfs2(root.getLeftChild(),result,path+"0",c);
            result=dfs2(root.getRightChild(),result,path+"1",c);
        }

        else if(root.getData()==c)
        {
            result=path;
            return result;
        }
        result=dfs2(root.getLeftChild(),result,path+"0",c);
        result=dfs2(root.getRightChild(),result,path+"1",c);

        return result;
    }
    private static String getPathTo(final BinaryNodeInterface<Character> root, char c)
    {
        // TO DO
        String result="";
        String path="";
        return dfs2(root,result,path,c);
    }
	
	/**
	 * Decompress the message using Huffman tree represented by treeString
	 * @param treeString the string represents the Huffman tree of the
	 * compressed message
	 * @param message the compressed message to be decompressed
	 * @return a string representing decompressed message
	 */
	public static String decompress(final String treeString, final String message)
	{
		// TO DO
        if(treeString.equals("") || message.equals("") || treeString.equals(null) || message.equals(null))
            return "";
        String decompressedMessage="";
        char[] c=treeString.toCharArray();
        char[] m = message.toCharArray();
        BinaryNode<Character> tree = createTreeFromString(treeString,0);
        BinaryNodeInterface<Character> tempTree = tree;

        //System.out.println(getTreeString(tree));
        for(int i=0; i<m.length+1; i++) {//Runs to the length of the string so that it runs the last time to get the last letter when decompressing, after the last letter gets added the loop breaks to not go out of bounds

            if (tempTree.getData() != null) {
                //System.out.println("Adding "+ tempTree.getData() + " at " + i);
                decompressedMessage += tempTree.getData();
                tempTree = tree;
                if (i == m.length)
                    break;
            }

            if (m[i] == '0') {
                //System.out.println("TEst");
                tempTree = tempTree.getLeftChild();

            } else if (m[i] == '1') {
                tempTree = tempTree.getRightChild();

            }


        }
		return decompressedMessage;	// Do not forget to change this line!!!
	}


    public static BinaryNode<Character> createTreeFromString(String treeString, int index)
    {
        //HuffmanTree tree = new HuffmanTree("");
        BinaryNode<Character> parent = new BinaryNode<Character>();
        if(treeString.equals("")|| (index>=treeString.length() ||global>=treeString.length() ))
            return parent;
        if(treeString.charAt(index)=='L')
        {
            global=index+1;
            //System.out.println(treeString.charAt(index+1));
            parent.setData(treeString.charAt(index+1));
            return parent;
        }
        else{
            if(!parent.hasLeftChild())
            {
                //System.out.println("Left Child " + treeString.charAt(index+1));
                parent.setLeftChild(createTreeFromString(treeString,index+1));
            }
            if(!parent.hasRightChild())
            {
                //System.out.println("Right Child " +treeString.charAt(global+1));
                parent.setRightChild(createTreeFromString(treeString, global+1));
            }
        }
        return parent;


    }

	
}

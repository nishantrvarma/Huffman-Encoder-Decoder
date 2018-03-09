

	import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


	public class Huffman_tree 
	
	{
		int size = 1000000;
		int[] frequencytable = new int[size];
		ArrayList<Integer> val = new ArrayList<Integer>();
		ArrayList<Integer> freq = new ArrayList<Integer>();
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		FileOutputStream out = null;
		PrintWriter writer = null;
		Scanner s = null;
		
		void frequency_table(String filename) throws Exception
		
		{		
			
				s = new Scanner(new BufferedReader(new FileReader(filename)));
				while(s.hasNext())
				
				{
					int num = s.nextInt();
					frequencytable[num] = frequencytable[num] + 1;
				}
				
				for(int i=0; i<size; i++)
				
				{
					if(frequencytable[i]!=0)
					{
						val.add(i);
						freq.add(frequencytable[i]);
					}
				}
			}
			
		
		
		void build_codetable(Node Root)
		
		{
			int path[] = new int[100000];
			compute_path(Root, path, -1);
		}
		
		void compute_path(Node node, int path[], int len) 
		
		{
			if (node == null)
				return;
			
			len++;
			
			if (node.left == null && node.right == null)
				assignment(node.val, path, len);
			else
			{
				path[len] = 0;
				compute_path(node.left, path, len);
				path[len] = 1;
				compute_path(node.right, path, len);
			}
		}
		
		void assignment(int val, int arr[], int len)
		
		{
			int i;
			String code = "";
			for(i=0; i<len; i++){
				code = code + arr[i];
			}
			map.put(val, code);
		}
		
		void txtout() throws IOException
		
		{	
			try{
				writer = new PrintWriter("code_table.txt", "UTF-8");
				
				for(int k:map.keySet())
				
				{
					writer.println(k +" " +map.get(k));
				}
				
			}
			catch(IOException e)
			
			{
				e.printStackTrace();
			}
			finally
			{
				writer.close();
			}
		}
		
		void binout(String fileName) throws NumberFormatException, IOException
		
		{
			try {
				s = new Scanner(new BufferedReader(new FileReader(fileName)));
				out = new FileOutputStream("encoded.bin");
				StringBuffer binaryString = new StringBuffer("");
				
				while(s.hasNext())
				
				{
					int entry = s.nextInt();
					binaryString = binaryString.append(map.get(entry));
					int ct=0;
					if(binaryString.length()>=8)
					
					{
						int pos = 0;
						while(pos < binaryString.length()-7)
						{
							ct++;
							byte nxt = 0x00;
							for(int i=0;i<8 && pos+i < binaryString.length(); i++)
							{
								nxt = (byte) (nxt << 1);
								nxt += binaryString.charAt(pos+i)=='0'?0x0:0x1;
							}
							out.write(nxt);
							pos+=8;
						}
					}
					binaryString = new StringBuffer(binaryString.substring(ct*8));
				}			
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		
		void build_decode_tree(String codefilename, String encodedfilename)
		
		{
			try {
				s = new Scanner(new BufferedReader(new FileReader(codefilename)));
				Node_Decode node = new Node_Decode(0);
				Node_Decode root = node;
				while(s.hasNext())
				
				{
					int val = s.nextInt();
					String code = s.next();
					for(int i=0; i<code.length(); i++)
					
					{
						Node_Decode end = new Node_Decode(0);
						if(code.charAt(i)=='0'  && node.left==null)
						{
							node.left = end;
							node= node.left;
						}
						else if(code.charAt(i)=='0'  && node.left!=null)
						{
							node = node.left;
						}
						else if(code.charAt(i)=='1'  && node.right==null)
						{
							node.right = end;
							node= node.right;
						}
						else
						{
							node = node.right;
						}
					}
					node.frequency = val;
					node = root;
				}
				decode(node, encodedfilename);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		
		
		void decode(Node_Decode endnode, String encodedfilename)
		
		{
			try {
				FileInputStream in = new FileInputStream(encodedfilename);
				Node_Decode temp = endnode;
				writer = new PrintWriter("decoded.txt", "UTF-8");
				byte[] filedata = new byte[(int) new File(encodedfilename).length()];
				in.read(filedata);
				String tempbit = "";
				for(int i=0; i< filedata.length; i++)
				{
					tempbit = tempbit + String.format("%8s", Integer.toBinaryString(filedata[i] & 0xFF)).replace(' ', '0');
					for(int j=0; j<tempbit.length(); j++)
					{
						if(tempbit.charAt(j)=='0')
						{
							temp = temp.left;
						}
						else
						{
							temp = temp.right;
						}
						if(temp.left==null && temp.right==null)
						
						{
							
							writer.print(temp.frequency +"\n");
							temp = endnode;
							if(j+1<tempbit.length())
								tempbit = tempbit.substring(j+1);
							else
								tempbit = "";
							j=-1;
						}
					}
					temp=endnode;
				}
				in.close();
				writer.close();
				
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			
			{
				e.printStackTrace();
			}
		}	
		
	
	}
	
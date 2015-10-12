import java.util.*;



enum color{ black, red}

class TNode{

	public int val;
	public TNode left;
	public TNode right;
	public TNode par;
	public color cl;
	public int level = -1;

}


class Tree{

	public static TNode root= null;
	public LinkedList<LinkedList<TNode>> levels= new LinkedList<LinkedList<TNode>>();
	public static LinkedList<TNode> inOrder= new LinkedList<TNode>();
	public static LinkedList<TNode> preOrder= new LinkedList<TNode>();
	public static LinkedList<TNode> postOrder= new LinkedList<TNode>();
	
	public static void addN_BST(TNode tn){
	
		TNode curr = new TNode();
		
		if (root == null)
			root = tn;
		else{
			curr = root;
			while (curr.left!= null || curr.right != null){
				if (tn.val <= curr.val)
					if(curr.left != null)
						curr = curr.left;
					else
						break;
				else
					if(curr.right != null)
						curr = curr.right;
					else
						break;
			}
			if(tn.val <= curr.val){
				curr.left = tn;
				tn.par = curr;
			}
			
			else{
				curr.right = tn;
				tn.par = curr;
			}
			
		}
	
	}
	
	
	/*public static void find_levels(TNode rooti , int lvl){
	
		if(lvl == 0){
			rooti.level = lvl;
			LinkedList<TNode> lvls= new LinkedList<TNode>();
			levels.add(lvls);
			levels.get(0).add(rooti);
			
		}
		if (levels.size() <= lvl+1){
			LinkedList<TNode> lvls= new LinkedList<TNode>();
			levels.add(lvls);
		}
		if(rooti.left != null){
			rooti.left.level = lvl+1;
			levels.get(lvl+1).add(rooti.left);
			find_levels(rooti.left , lvl+1);
		}
		if(rooti.right != null){
			levels.get(lvl+1).add(rooti.right);
			rooti.right.level = lvl+1;
			find_levels(rooti.right , lvl+1);
		}
		else
			return;
	
	}*/
	
	
	
	
	
	public static TNode buildBSTFromArr(int[] a, int start , int end){
		
		if (end<= start){
			return null;
		}
			
			TNode tn = new TNode();
			int mid;
			mid = ((start + end)/2);
			tn.val = a[mid];
			tn.par = null;
			tn.cl = color.black;
			tn.left = buildBSTFromArr(a, start , mid-1);
			tn.right = buildBSTFromArr(a , mid+1 , end);
			return tn;
		
	}
	
	public static int check_getHeight(TNode T){
	
		if (T == null)
			return 0;
		int leftH = check_getHeight(T.left);
		if (leftH == -1)
			return -1;
		
		int rightH = check_getHeight(T.right);
		if (rightH == -1)
			return -1;
			
		if(Math.abs(leftH - rightH)>1)
			return -1;
			
		return (Math.max(leftH,rightH)+1);
		
	
	}
	
	public static int getHeight(TNode T){
		if(T==null)
			return 0;
		return (Math.max(getHeight(T.left) , getHeight(T.right)) + 1);
	}
	
	public static boolean isBalanced(TNode root){
		if (root == null)
			return true;
			
		return (Math.abs(getHeight(root.left) - getHeight(root.right)) < 2);
	}
	
	public static boolean check_BST(TNode tno, Integer min , Integer max){
		
		if(tno == null)
			return true;
		if((min != null && tno.val <= min) || (max!= null && tno.val >max))
			return false;
		if(!check_BST(tno.left , min , tno.val) || !check_BST(tno.right, tno.val , max))
			return false;
			
			
		return true;
	
	}
	
	
	public static void in_order(TNode roo){
		if(roo == null)
			return;
		in_order(roo.left);
		inOrder.add(roo);
		in_order(roo.right);
	
	}
	
	
	public static void pre_order(TNode roo){
		
		if(roo == null)
			return;
		preOrder.add(roo);
		pre_order(roo.left);
		pre_order(roo.right);
		
	}
	
	
	public static void post_order(TNode roo){
	
		if(roo == null)
			return;
		post_order(roo.left);
		post_order(roo.right);
		postOrder.add(roo);
	
	}
	
	
	public static TNode next_inOrder(TNode tn){
		
		if(tn.right != null){
			tn = tn.right;
			//System.out.println(tn.val);
			while (tn.left != null)
				tn = tn.left;
			return tn;
		}
		else{
			tn = tn.par;
			while(tn.par != null && tn.par.left != tn)
				tn = tn.par;
			return tn;
		
		}
	
	}
	
	

}
 public class main{
 
 	public static void main(String[] args){
 		
 		Tree mala = new Tree();
 		TNode ti = new TNode();
 		int[] a = {5,2,3,1,8,6,9};
 		System.out.println("ha?");
 		//ti = Tree.buildBSTFromArr(a,0,9);
 		
 		for (int i:a){
 			TNode tn = new TNode();
 			tn.val = i;
 			mala.addN_BST(tn);
 			
 		}
 		
 		/*for(int j = 0 ; j < mala.levels.size(); j++){
 			for(int i = 0 ;i < mala.levels.get(i).size() ; i++){
 				System.out.println(mala.levels.get(i).get(j).val);
 			}
 		}*/
 		
 		System.out.println(mala.root.val);
 		System.out.println(Tree.check_getHeight(mala.root));
 		System.out.println(Tree.check_BST(mala.root , null, null));
 		System.out.println(Tree.next_inOrder(mala.root).val);
 		
 		Tree.in_order(mala.root);
 		
 		for (TNode tnn : mala.inOrder)
 			System.out.println(tnn.val);
 		
 		//mala.find_levels(mala.root , 0);
 		
 		
 		
 		System.out.println("done!");
 		
 	
 	}
 
 }


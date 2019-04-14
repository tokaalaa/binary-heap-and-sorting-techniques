package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

public class MyHeap<T extends Comparable<T>> implements IHeap<T>{
	private ArrayList<INode<T>> heap = new ArrayList<INode<T>>();
	private int size = 0;
	@Override
	public INode<T> getRoot() {
		// TODO Auto-generated method stub
		if(size > 0)
			return heap.get(0);
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void heapify(INode<T> node) {
		// TODO Auto-generated method stub
		if(node != null) {
		INode<T> left = node.getLeftChild();
		INode<T> right = node.getRightChild();
		INode<T> largest;
		if(left != null && left.getValue().compareTo(node.getValue()) > 0) {
			largest = left;
		} else {
			largest = node;
		}
		if(right != null && right.getValue().compareTo(largest.getValue()) > 0) {
			largest = right;
		}
		if(largest != node) {
			swap(node,largest);
			if(node != getRoot())
				heapify(node.getParent());
			heapify(largest);
		}
		}
	}

	@Override
	public T extract() {
		// TODO Auto-generated method stub
		if(size == 0) {
			return null;
		}
		T root = getRoot().getValue();
		if(size == 1) {
			heap.remove(0);
			size--;
		} else if(root != null) {
			heap.get(0).setValue(heap.get(size - 1).getValue());
			heap.remove(size - 1);
			size--;
			heapify(heap.get(0));
		}
		return root;
	}

	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub
	if(element != null) {
		INode<T> n = new Node<T>(size);
		n.setValue(element);
		heap.add(n);
		size++;
		heapify(heap.get(size - 1).getParent());
		}
	}

	public void swap(INode<T> n1, INode<T> n2) {
		// TODO Auto-generated method stub
		T temp = n1.getValue();
		n1.setValue(n2.getValue());
		n2.setValue(temp);
	}

	@Override
	public void build(java.util.Collection<T> unordered) {
		// TODO Auto-generated method stub
		heap = new ArrayList<INode<T>>();
		if (unordered != null) {
			size = unordered.size();
			Iterator<T> iterator = unordered.iterator();
			int j = 0;
			while(iterator.hasNext()) {
				INode<T> n = new Node<T>(j++);
				n.setValue(iterator.next());
				heap.add(n);
			}
			for(int i = size/2 - 1; i >= 0 ;i--) {
				heapify(heap.get(i));
			}
		}
	}


	@SuppressWarnings("hiding")
	class Node<T extends Comparable<T>> implements INode<T> {

		private int index;
		private T value;
		public Node(int i) {
			index = i;
		}
		@SuppressWarnings("unchecked")
		@Override
		public INode<T> getLeftChild() {
			// TODO Auto-generated method stub
			int left = 2 * index + 1;
			if (left < size) {
				return (INode<T>) heap.get(left);
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public INode<T> getRightChild() {
			// TODO Auto-generated method stub
			int right = 2 * index + 2;
			if (right < size) {
				return (INode<T>)heap.get(right);
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public INode<T> getParent() {
			// TODO Auto-generated method stub
		if(size == 1) {
			return null;
		}
		int parent = (index - 1) / 2;
		if (parent >= 0) {
			return (INode<T>)heap.get(parent);
		}
			return null;
		}

		@Override
		public T getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public void setValue(T value) {
			// TODO Auto-generated method stub
			this.value = value;
		}
		
	}


	public ArrayList<INode<T>> getArrHeap() {
		// TODO Auto-generated method stub
		return heap;
	}

	public void setSize(int n) {
		// TODO Auto-generated method stub
		size = n;
	}

}

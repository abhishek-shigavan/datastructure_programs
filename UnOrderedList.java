package com.abhi.dsprogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * UnOrderedList	-- This program reads file & add words 
 * 			   to linked list & take word from user 
 * 			   to search if word is present then 
 *			   delete it / if not then add it.
 * 
 * 
 * @author Abhishek Shigavan
 *
 */
public class UnOrderedList {
	
	Node head; // head of list
	Node tail; // tail of list
/**
 * This class defines node properties
 *
 */
    static class Node {
   
        String data;
        Node next;
   
        // Constructor
        Node(String d)
        {
            data = d;
            next = null;
        }
    }
/**
 * 
 * This method add new node into 
 * linked list
 *    
 * @param list
 * @param data
 * @return list
 */
    public static UnOrderedList addInLinkedList(UnOrderedList list, String data) {
    	
        // Create a new node with given data
        Node new_Node = new Node(data);
        new_Node.next = null;
   
        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
         
        	list.head = new_Node;
        }
        else if (list.tail == null) {
        	//making second new node as tail
        	list.head.next = new_Node;
        	list.tail = new_Node;
        }
        else {
            // Else adding new node at tail 
           
            Node last = list.tail;
            // Insert the new_Node at last node
            last.next = new_Node;
            list.tail = new_Node;
        }
   
        // Return the list by head
        return list;
    }
/**
 * This method searches the given
 * word into given list
 *     
 * @param list
 * @param word
 * @return No return
 */
    public static void searchList(UnOrderedList list, String word) {
    	
    	boolean flag = false;
    	//storing head into tempNode
    	Node tempNode = list.head;
    
    	//for searching word at head
    	if (tempNode.data.compareTo(list.head.data) == 0 && tempNode.data.compareTo(word) == 0) {
    			
    		//deleting head
    		Node headNext = list.head.next;
    		list.head = headNext;
    		System.out.println("Got the word at head position....Deleted word Sucessfully.");
    		flag = true;
    	}
    	
    	//taking previous node
    	Node prevNode = list.head;
    	//taking next node of head
    	tempNode = list.head.next;
    	
    	//for searching word between node next to head
    	// & second last node
    	while(tempNode.next != null) {	
    	
    		if(tempNode.data.compareTo(word) == 0) {
    			
    			Node nextNode = tempNode.next;
    			prevNode.next = nextNode;
    			System.out.println("Got the word....Deleted word Sucessfully");
    			flag = true;
    			break;
    		}
    		//storing current node as previous node for next iteration
    		prevNode = tempNode;
    		//taking next node as temp for next iteration
    		tempNode = tempNode.next;
    	}
    	
    	//for searching word at tail
    	tempNode = list.tail;
    	
    	if(tempNode.data.compareTo(word) == 0) {
    		
    		list.tail = prevNode;
    		System.out.println("Got the word at tail....Deleted word Sucessfully");
    		flag = true;
    	}
    	
    	//if word is not found
    	if(flag == false) {
    		
    		Node new_Node = new Node(word);
    		Node tempLast = list.tail;
    		tempLast.next = new_Node;
    		list.tail = new_Node;
    		System.out.println("Word Not Found.... Added Word To List");
    	}
    }
/**
 * This method prints the list
 *    
 * @param list
 */
    public static void printList(UnOrderedList list) {

    	Node currNode = list.head;
    
        System.out.print("LinkedList: ");
    
        // Traverse through the LinkedList
        while (currNode != null) {
           
        	// Print the data at current node
            System.out.print(currNode.data + " ");
    
            // Go to next node
            currNode = currNode.next;
        }
    }
/**
 * This method reads file & store words in array
 * then pass these words as data to add in linked list
 * 
 * Take word from user & pass it to searchList method
 * to search it in linked list . According to search
 * print the result
 *      
 * @param args
 * @throws FileNotFoundException
 */
    public static void main(String[] args) throws FileNotFoundException {
    
    	//Creating empty list. 
        UnOrderedList list = new UnOrderedList();
        //creating empty string
        String wordList = "";
		
        //reading file
	    Scanner inFile = new Scanner(new File("C:\\Users\\Abhi\\WordList.txt")).useDelimiter(",\\s*");
	    Scanner sc = new Scanner(System.in);

	    while (inFile.hasNext()) {
	    
	    	// find next line
	    	wordList = inFile.next();
	    }
	   
	    //converting to lower case
	    String lowerCaseWord = wordList.toLowerCase();
	    //storing words into array 
	    String[] words = lowerCaseWord.split("\\s");
	    
	    //add words to linked list
	    for(int i =0; i < words.length; i++) {
	    	
	    	list = addInLinkedList(list,words[i]);
	    }
	    
	    System.out.println("Enter Word To Search : ");
	    String word = sc.next().toLowerCase();
	    
	    // search the word
	    searchList(list, word);
   
        // Print the LinkedList
        printList(list);
    }
}

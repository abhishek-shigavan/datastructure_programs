package com.abhi.dsprogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * OrderedList	--	This program reads number from file
 * 			& store it in ascending order into 
 * 			LinkedList. Takes number from user
 * 			search it in linked list if number
 * 			found then pop it else add it.
 * 
 * @author Abhishek Shigavan
 *
 */
public class OrderedList {
	
	Node head; // head of list
	Node tail; // tail of list
/**
 * Defining node properties	
 *
 */
    static class Node {
   
        int data;
        Node next;
   
        // Constructor
        Node(int d)
        {
            data = d;
            next = null;
        }
    }
/**
 * This method compares the new number entering into
 * linked list with present number & store it in ascending
 * order
 *     
 * @param list - List to store numbers
 * @param data - number
 * @return list - sorted list
 */
	public static OrderedList sortedAdd(OrderedList list, int data) {
		
		Node newNode = new Node(data);
		
        if(list.head == null ){
            //making 1st node as head
            list.head = newNode;
        }
        else if(list.head.data > newNode.data){
        	 //if new key is less than head key then making new node as head
        	
        	Node tempNode = list.head;
            //setting old head as next to new head
            newNode.next = tempNode;
            //making new node as head
            list.head = newNode;
        }
        else{
            //storing head into current node
            Node currentNode = list.head;
            //loop will find node previous to max node
            while(currentNode.next != null && currentNode.next.data < newNode.data) {

                    currentNode = currentNode.next;
            }

            //making new node as tail as it is max among all nodes
            if(currentNode.next == null) {

                currentNode.next = newNode;
                list.tail = newNode;
            }
            else {
                //getting node with max key
                Node maxNode = currentNode.next;
                //setting new node as next node of previous node of max
                currentNode.next = newNode;
                //setting max node as next node of new node
                newNode.next = maxNode;
            }
        }
		return list;
	}
/**
 * This method search given number in list
 * If number is there then pop it from list
 * Else add number in list at appropriate position	
 * 
 * @param list - list of numbers in sorted manner
 * @param number - number to search
 * @return list - updated list of numbers
 */
	public static OrderedList searchNum(OrderedList list, int number) {
		
		boolean flag = false;
		
		if(list.head.data == number) {
			//poping head
			
			Node tempNode = list.head.next;
			list.head = tempNode;
			System.out.println("Got ur Number at Head position");
			flag = true;
		}
		else {
			
			Node currentNode = list.head.next;
			Node prevNode = list.head;
			//iterate till second last node
			while (currentNode.next != null) {
				//checking number
				if(currentNode.data == number) {
					
					//poping number
					Node nextNode = currentNode.next;
					prevNode.next = nextNode;
					System.out.println("Got ur Number...!!!");
					flag = true;
					break;
				}
				//updating previous & current node
				prevNode = currentNode;
				currentNode = currentNode.next;
			}
			
			Node lastNode = currentNode;
			//checking for number matches last position 
			//& it is last position of list
			if (lastNode.data == number & flag == false) {
				//poping last node
				prevNode.next = null;
				System.out.println("Got ur Number at Last Position...!!!");
				flag = true;
			}      
		}
		//if number is not found
		if(flag == false) {
			
			System.out.println("No Such Number Present In List...Adding "+number+" into List .");
			list = sortedAdd(list, number);
		}
		
		return list;
	}
/**
 * This method prints the list
 *    
 * @param list
 */
	public static void printList(OrderedList list) {

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
 * This method reads NumberList file and stores number in list
 * Then Pass these nos to sortedAdd method to store it in linked list in 
 * sorted manner
 * Take a number form user to search it in linked list
 *   	
 * @param args
 * @throws FileNotFoundException
 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//reading file
		Scanner inFile = new Scanner(new File("C:\\Users\\Abhi\\NumberList.txt"));
		Scanner sc = new Scanner(System.in);
		
		//linked list
		OrderedList list = new OrderedList();
		//list to store numbers from file
		List<Integer> numberList = new ArrayList<>(); 
		
		while (inFile.hasNextInt()) {
			//adding number to list  
			numberList.add(inFile.nextInt());
		}
		
		//converting ArrayList into array 
		Integer numberArray[] = new Integer [numberList.size()];
		numberArray =  numberList.toArray(numberArray);
		
		for(int i=0; i < numberArray.length; i++) {
			 
			int number = numberArray[i];	
			list = sortedAdd(list, number);
		}
		
		System.out.println("Enter Number to Search : ");
		int number = sc.nextInt();
		
		list = searchNum(list, number);
		
		printList(list);
	}
}

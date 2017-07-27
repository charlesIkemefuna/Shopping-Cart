package com.checkout.charles;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Dictionary;
import java.util.Locale;

public class Shopper {
	private static int totalItems;
	private static Item[]items;//declare array to hold store items
	private static NumberFormat formatter;//this numberFormat object will determine currency for item prices
	
	public static void main(String[]args){
		System.out.println("WELCOME TO MY STORE");
		totalItems=getNumberOfItems("How many items do you want to buy?");
		items=new Item[totalItems];
		int count=0;
		while(count<totalItems){
			getItemDetails(count);
			count++;
		}
		displayCart();
	}
	private static double buy3ForThePriceOfTwo(int quantity,double price){
		if(quantity==3){
			price=price *quantity-price;
			return price;
		}
		else{
			price=price*quantity;
			return price;
		}
	}

	
	private static void displayCart() {
		formatter=NumberFormat.getCurrencyInstance(Locale.ENGLISH);
		System.out.println("#############################################################################################");
		System.out.println("Shopping Cart Details................");
		System.out.println("#############################################################################################");
		
		for(int i=0; i<totalItems; i++){
			showLineItem(i);
		}
		System.out.println("#############################################################################################");
		double subTotal=calculateSubTotal();
		double tax=subTotal*.0875;
		double total=subTotal+tax;
		System.out.println("SubTotal: "+formatter.format(subTotal));
		System.out.println("Sales tax @ 8.75%: "+formatter.format(tax));
		System.out.println("Total: "+formatter.format(total));
		System.out.println("Average Cost of All Items: "+formatter.format(subTotal/getTotalItems()));
		System.out.println("Thanks for Shopping with us!");
		
	}

	private static int getTotalItems() {
		int sum=0;
		for(int i=0; i<totalItems; i++){
			sum+=items[i].getQuantity();
		}
		return sum;
	}

	private static double calculateSubTotal() {
		double total=0;
		for(int i=0; i<totalItems; i++){
			total+=items[i].getPrice()*items[i].getQuantity();
		}
		return total;
	}

	private static void showLineItem(int index) {
		String quantityText=items[index].getQuantity()+"\t---\t"+items[index].getName();
		String priceText=formatter.format(items[index].getPrice())+ " = "+formatter.format(items[index].getPrice()*items[index].getQuantity());
		System.out.format("%-30s %20s%n",quantityText,priceText);
	}

	private static void getItemDetails(int index) {
		String name=getItemName("What is the name of item # "+(index+1)+"? ");
		double price=getItemPrice("What is the price of item #"+(index+1)+"? ");
		int quantity=getItemQuantity("How many of item #"+(index+1)+"? ");
		//buy3ForThePriceOfTwo(quantity,price);
		items[index]=new Item(name,price,quantity);
	}

	private static double getItemPrice(String item){
		System.out.print(item);
		Scanner sc=new Scanner(System.in);
		return sc.nextDouble();
	}
	
	private static String getItemName(String item){
		System.out.print(item);
		Scanner sc=new Scanner(System.in);
		return sc.nextLine();
	}
	
	private static int getItemQuantity(String item){
		System.out.print(item);
		Scanner sc=new Scanner(System.in);
		return sc.nextInt();
	}
	private static int getNumberOfItems(String item){
		System.out.print(item);
		Scanner sc=new Scanner(System.in);
		return sc.nextInt();
	}

}

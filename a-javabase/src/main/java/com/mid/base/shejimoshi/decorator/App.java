package com.mid.base.shejimoshi.decorator;

/**
 * 装饰器模式
 *  * 接口、具体对象、装饰类、具体装饰类
 *  * 装饰器模式主要用来优化业务的复杂度，它不仅简化了我们的业务代码，还优化了业务代码的结构设计，
 *  * 使得整个业务逻辑清晰、易读易懂。通常，装饰器模式用于扩展一个类的功能，且支持动态添加和删除类的功能。
 *  * 在装饰器模式中，装饰类和被装饰类都只关心自身的业务，不相互干扰，真正实现了解耦。
 */
public class App 
{
//    public static void main( String[] args ) throws InterruptedException, IOException
//    {
////    	IDecorator decorator = new Decorator();
////    	IDecorator curtainDecorator = new CurtainDecorator(decorator);
////    	curtainDecorator.decorate();
//        
//    	
//    }
	
	public static void alloc() {
		byte[] b = new byte[2];
		b[0] = 1;
	}
 
	public static void main(String[] args) {
		for(int i=0; i<1000000; i++) {
			add1(1,2,3,4);
		}
		
	}
	
	private static int add1(int x1, int x2, int x3, int x4) {
	    return add2(x1, x2) + add2(x3, x4);
	}
	private static int add2(int x1, int x2) {
	    return x1 + x2;
	}
	
}

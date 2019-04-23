package com.chinasofti.YaHui.dao.view;

import org.junit.Test;

public class View {
	
	//员工登录后界面
	@Test
	public void welcome(){
		
		
		System.out.println("=================================================");
		System.out.println("1、点菜");
		System.out.println("2、开卡");
		System.out.println("3、挂失");
		System.out.println("4、补卡");
		System.out.println("5、充值");
		System.out.println("6、查看余额");
		System.out.println("7、修改个人信息");
		System.out.println("0、返回登录界面");
		System.out.println("------------------------------");
	}
	//用于员工经理登录
	public void welcome2(){
		System.out.println("\t欢迎来到亚惠餐厅登录系统");
		System.out.println("=========================================");
		System.out.println("1、请登录");
		System.out.println("0、退出");
}
		public void welcome3(){
			System.out.println("<<<点菜");
			System.out.println("1、进行点菜");
			System.out.println("2、进入购物车");
			System.out.println("3、结账");
			System.out.println("0、返回");
		}
		public void welcome4(){
			//购物车
			System.out.println("1、进行点菜");
			System.out.println("2、修改所选菜");
			System.out.println("3、删除所选菜");
			
			System.out.println("0、返回");
		}
		public void welcome5(){
			System.out.println("----------------------------");
			System.out.println("1、菜品管理");
			System.out.println("2、员工管理");
			System.out.println("3、客户冻结");
			System.out.println("4、会员优惠管理");
			System.out.println("5、统计");
			System.out.println("6、修改个人信息");
			System.out.println("0、退出");
		}
		public void welcome6(){
			System.out.println("<<<员工管理");
			System.out.println("1、添加员工");
			System.out.println("2、删除员工");
			System.out.println("3、修改员工信息");
			System.out.println("4、查询员工");
			System.out.println("0、返回");
		}
		public void welcom7(){
			System.out.println("<<<菜类管理");
			System.out.println("1、添加菜类");
			System.out.println("2、删除菜类");
			System.out.println("3、更改菜类");
			System.out.println("4、查询所有菜类");
			System.out.println("0、返回");
		}
		public void welcom8(){
			System.out.println("<<<菜食管理");
			System.out.println("1、添加菜");
			System.out.println("2、删除菜");
			System.out.println("3、更改菜");
			System.out.println("4、查询所有菜");
			System.out.println("0、返回");
		}
		public void welcom9(){
			System.out.println("<<<员工管理");
			System.out.println("1、添加员工");
			System.out.println("2、删除员工");
			System.out.println("3、更改员工信息");
			System.out.println("4、查询所有员工");
			System.out.println("0、返回");
		}
		public void println(String msg){
		System.out.println(msg);
	}
	
}
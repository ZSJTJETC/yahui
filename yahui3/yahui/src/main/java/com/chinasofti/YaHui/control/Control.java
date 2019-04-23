package com.chinasofti.YaHui.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.synth.SynthStyle;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import com.chinasofti.YaHui.dao.view.View;
import com.chinasofti.YaHui.domain.Address;
import com.chinasofti.YaHui.domain.Card;
import com.chinasofti.YaHui.domain.Emp;
import com.chinasofti.YaHui.domain.Food;
import com.chinasofti.YaHui.domain.FoodType;
import com.chinasofti.YaHui.domain.Order;
import com.chinasofti.YaHui.domain.Shopping;
import com.chinasofti.YaHui.domain.VIP;
import com.chinasofti.YaHui.util.UserInput;

public class Control {
	//属性
	private View v;
	private UserInput ui;
	public static final String IP="10.10.49.23";
	public static final int PORT=8520;
	private YaHuiService service;
	int select20=0;
	//构造方法

	public Control() {
		this.v =new View();
		this.ui =new UserInput();
		//创建代理对象
		service=ProxyClient.getClient(YaHuiService.class,IP,PORT);
	}

	//自定义方法-项目流程方法
	@Test
	public void start(){
		while(true){
			
			this.v.welcome2(); 
			int select=this.ui.getInt("请选择：");

			if(select==0){
				this.v.println("系统终止");
				System.exit(0);
			}else if(select==1){
				select20=this.ui.getInt("请输入用户id:");
				String select3=this.ui.getString("请输入用户密码:");
				boolean b = this.service.ifEmp(select20, select3);
				//判断登录的是会员还是经理
				if(b==true){
					int i = service.ifEmpPower(select20);
					if(i==0){
						String s=service.findbyId(select20).getEname();
						System.out.println("\t欢迎员工"+s+"来到亚惠餐厅");
						Employee();
	
					}else{
						String s=service.findbyId(select20).getEname();
						System.out.println("\t欢迎经理"+s+"来到亚惠餐厅");
						
						Manager();
					}
				}else{
					System.out.println("密码输入错误");
				}
			}
		}
	}
	//员工登录后
	private void Employee(){
		while(true){
			
			this.v.welcome();
			int select4=this.ui.getInt("请选择：");
			if(select4==0){
				this.v.println("返回登录界面");
				start() ;
			}else if(select4==1){
				//点菜的方法
				String info=this.addFood();
				this.v.println(info);
			}else if(select4==2){
				//开卡的方法
				this.openCard();	
			}
			else if(select4==3){
				//挂失的方法
				this.reportCard();	
			}else if(select4==4){
				//补卡的方法
				this.reissueCard();	
			}else if(select4==5){
				//充值的方法
				this.rechargeCard();	
			}else if(select4==6){
				//查询余额的方法
				this.balanceCard();	
			}else if(select4==7){
				//修改个人信息的方法
				this.modifyEmployee();	
			}
		}
	}
	//经理登录后
	private void Manager(){
		while(true){
			this.v.welcome5();
			int select5=this.ui.getInt("请选择：");
			if(select5==0){
				this.v.println("返回登录界面");
				start();
			}else if(select5==1){
				//菜品管理的方法
				this.managerFood();	
			}else if(select5==2){
				//员工管理的方法
				this.managerEmployee();	
			}else if(select5==3){
				//客户冻结的方法
				this.frostEmployee();	
			}else if(select5==4){
				//会员优惠额度管理的的方法
				this.discountCord();	
			}else if(select5==5){
				//统计月销售量以及客户喜欢的菜的方法
				this.statistics();	
			}else if(select5==6){
				//修改个人信息的方法
				this.modifyManager();
			}
		}
	}
	//修改经理个人信息的方法
	private void modifyManager() {
		//修改经理自身的姓名，性别，密码
		while(true){
			
			String select2=this.ui.getString("请输入修改后的姓名:");
			String select3=this.ui.getString("请输入修改后的性别:");
			String select4=this.ui.getString("请输入修改后的密码:");
			String updateImg = service.updateImg(select20, select2, select4, select3);
			System.out.println(updateImg);
			this.Manager();
			}
		
	}

	//统计月销售量以及客户喜欢的菜的方法
	private void statistics() {
		//显示所有菜的id，价格，月销售量，以及客户最喜欢的菜是。
		System.out.println("<<<统计");
		System.out.println("用户最喜欢的菜是:"+service.NnmMax().getfName());
		System.out.println("菜id\t菜名\t单价\t月销售量\t类型id");
		Food nnmMax = service.NnmMax();
		System.out.println(nnmMax);
		List<Emp> list = service.findAll();
		int eid=0;
		int assess=0;
		for (Emp emp : list) {
			if(emp.getAssess()>assess){
				eid=emp.getEid();
				assess=emp.getAssess();
			}
		}
		System.out.println("--------------------------------------");
		System.out.println("客户最满意的员工是");
		System.out.println("员工id\t员工姓名\t员工性别\t员工权限\t员工地址\t满意度");
		Emp emp2 = service.findbyId(eid);
		System.out.println(emp2);
		for (Emp emp : list) {
			if(emp.getAssess()<assess){
				eid=emp.getEid();
				assess=emp.getAssess();
			}
		}
		System.out.println("客户最不满意的员工是");
		System.out.println("员工id\t员工姓名\t员工性别\t员工权限\t员工地址\t满意度");
		Emp emp3 = service.findbyId(eid);
		System.out.println(emp3);
	}

	//会员卡的优惠额度的方法
	private void discountCord() {
		while(true){
			System.out.println("<<<会员优惠管理");
			List<VIP> list = service.findAllVIP();
			System.out.println("vip等级\tvip名称\tvip折扣");
			for (VIP vip : list) {
				System.out.println(vip);
			}
			int i = this.ui.getInt("请输入要修改的会员卡级别编号:");
			double double2 = this.ui.getDouble("请输入超级会员卡优惠额度:");
			String s = this.ui.getString("是否确认更改(y/n)");
		if(s.equals("y")){
			String update = service.update(double2, i);
			System.out.println(update);
		}
		String ss = this.ui.getString("是否继续修改(y/n)");
		if(ss.equals("n")){
			this.Manager();
		}
		
	}
	}
	//冻结客户的方法
	private void frostEmployee() {
		while(true){
		System.out.println("<<<冻结客户");
		List<Card> list = service.findAllCard();
		for (Card card : list) {
			System.out.println(card);
		}
		int select=this.ui.getInt("请输入要冻结客户的卡id");
		String freezeCard = service.FreezeCard(select);
		System.out.println(freezeCard);
		String s = this.ui.getString("是否继续冻结客户(y/n)");
		if(s.equals("n")){
			this.Manager();
		}
		}
	}
	//员工管理的方法，增删改查员工
	private void managerEmployee() {
		while(true){
			this.v.welcom9();
			int select=this.ui.getInt("请选择：");
			if(select==0){
				this.v.println("返回");
				Manager();
			}else if(select==1){
				//添加员工的方法
				this.addEmployee();
			}else if(select==2){
				//删除员工的方法
				this.removeEmployee();
			}else if(select==3){
				//修改员工信息的方法
				this.updateEmployee();
			}else if(select==4){
				//查询员工信息的方法
				this.selectEmployee();
			}
		}

	}
	//添加员工的方法
	private void addEmployee() {
		while(true){
			System.out.println("<<<添加员工");
			//员工的名字，性别等。
			String ename = this.ui.getString("请输入员工姓名:");
			String epasswd = this.ui.getString("请输入员工密码:");
			String sex = this.ui.getString("请输入员工性别:");
			int epower = this.ui.getInt("请输入员工权限:");
			System.out.println("地址id\t地址");
			List<Address> list = service.FindAllAddress();
			for (Address address : list) {
				System.out.println(address);
			}
			int a = this.ui.getInt("请输入员工所在店铺id:");
			String address = service.FindAddressById(a);
			Address address2 = new Address(a, address);
			String addEmp = this.service.addEmp(ename, epasswd, sex, epower, address2);
			System.out.println(addEmp);
			String s = this.ui.getString("是否继续添加(y/n)");
			if(s.equals("n")){
				this.managerEmployee();
			}
		}
	}
	
	//删除员工的方法
	private void removeEmployee() {
		while(true){
			System.out.println("<<<删除员工");
			System.out.println("员工id\t员工姓名\t员工性别\t员工权限\t员工地址");
			List<Emp> list = service.findAll();
			for (Emp emp : list) {
				System.out.println(emp);
			}
			int select=this.ui.getInt("请输入要删除的员工id:");
			Emp emp = service.findbyId(select);
			if(emp!=null){
				System.out.println(emp);
				String s = this.ui.getString("是否确认删除(y/n)");
				if(s.equals("n")){
					this.managerEmployee();
				}else{
				service.deleteEmp(select);
				System.out.println("删除成功");
				break;
				}
			}else{
				System.out.println("该员工不存在！请重新输入！");
			}
			
		}
		
	}

	//修改员工信息的方法
	private void updateEmployee() {
		while(true){
			System.out.println("<<<修改员工地址");
			System.out.println("员工id\t员工姓名\t员工性别\t员工权限\t员工地址");
			List<Emp> list = service.findAll();
			for (Emp emp : list) {
				System.out.println(emp);
			}
			int select=this.ui.getInt("请输入要修改的员工id:");
			Emp emp = service.findbyId(select);
			System.out.println("地址id\t地址");
			List<Address> l = service.FindAllAddress();
			for (Address address : l) {
				System.out.println(address);
			}
			int i = this.ui.getInt("请输入修改后的地址id:");
			String id = service.FindAddressById(i);
			String string = service.updateAddress(select, new Address(i, id));
			String s = this.ui.getString("是否继续修改(y/n)");
			if(s.equals("n")){
				this.managerEmployee();
			}
		}
		
	}

	//查询员工信息的方法
	private void selectEmployee() {
		while(true){
			System.out.println("<<<查询员工信息");
			//直接显示所有员工以及信息
			System.out.println("员工id\t员工姓名\t员工性别\t员工权限\t员工地址\t满意度");
			List<Emp> list = service.findAll();
			for (Emp emp : list) {
				System.out.println(emp);
			}
			System.out.println("-------------------------------------------------------------");
			this.managerEmployee();
			
		}
		
	}

	//菜品管理的方法，包含菜类以及菜
	private void managerFood() {
		while(true){
		System.out.println("<<<菜品管理");
		System.out.println("1、菜类管理 ");
		System.out.println("2、菜食管理 ");
		System.out.println("0、返回");
		int select=this.ui.getInt("请选择：");
		if(select==0){
			this.v.println("返回");
			Manager();
		}else if(select==1){
			//菜类管理方法
			this.Vegetables();
		}else if(select==2){
			//菜的管理方法
			this.greens();
		}
		}
		
	}
	//菜的管理方法
	private void greens() {
		while(true){
			this.v.welcom8();
			int select=this.ui.getInt("请选择：");
			if(select==0){
				this.v.println("返回");
				Manager();
			}else if(select==1){
				//添加菜的方法
				this.addGreens();
			}else if(select==2){
				//删除菜的方法
				this.removeGreens();
			}else if(select==3){
				//更改菜的方法
				this.updateGreens();
			}else if(select==4){
				//查询所有菜的方法
				this.selectGreens();
			}
			}
	}
	//添加菜的方法
	private void addGreens() {
		while(true){
			System.out.println("<<<添加菜");
			List<FoodType> list = service.FindAllType();
			System.out.println("类型id\t类型名称");
			for (FoodType foodType : list) {
				System.out.println(foodType);
			}
			String addFood = this.service.addFood(this.ui.getString("请输入菜的名字"), this.ui.getDouble("请输入菜的价格"), this.ui.getInt("请输入菜的类型id"));
			System.out.println(addFood);
			String s = this.ui.getString("是否继续添加(y/n)");
			if(s.equals("n")){
				this.greens();
			}
			
		}
		
	}

	//删除菜的方法
	private void removeGreens() {
		while(true){
			System.out.println("<<<删除菜");
			System.out.println("类型id\t类型名称");
			List<FoodType> list = service.FindAllType();
			for (FoodType foodType : list) {
				System.out.println(foodType);
			}
			int i = this.ui.getInt("请输入要查询的菜的类型id");
			List<Food> list2 = service.findFood(i);
			System.out.println("菜品id\t菜品名称\t菜品价格\t销售量\t类型id");
			for (Food food : list2) {
				System.out.println(food);
			}
			int select=this.ui.getInt("请输入要删除的菜id:");
			String food = service.delFood(select);
			System.out.println(food);
			String s = this.ui.getString("是否继续删除(y/n)");
			if(s.equals("n")){
				this.greens();
			}
		}
		
	}

	//更改菜的方法
	private void updateGreens() {
		while(true){
			System.out.println("<<<更改菜的信息");
			System.out.println("类型id\t类型名称");
			List<FoodType> list = service.FindAllType();
			for (FoodType foodType : list) {
				System.out.println(foodType);
			}
			int i = this.ui.getInt("请输入要查询的菜的类型id");
			List<Food> list2 = service.findFood(i);
			System.out.println("菜品id\t菜品名称\t菜品价格\t销售量\t类型id");
			for (Food food : list2) {
				System.out.println(food);
			}
			
			System.out.println("1、修改菜名");
			System.out.println("2、修改价格");
			
			int ii = this.ui.getInt("请选择:");
			if(ii==1){
				int select=this.ui.getInt("请输入要更改的菜id:");
				
				Food food = service.findFoodById(select);
				if(food!=null){
					String s = this.ui.getString("菜名修改为:");
					String name = service.updateFoodName(select, s);
					System.out.println(name);
				}
				
			}else if(ii==2){
				int select=this.ui.getInt("请输入要更改的菜id:");
				Food food = service.findFoodById(select);
				if(food!=null){
				double s = this.ui.getDouble("价格修改为:");
				String name = service.updateFoodPrice(select, s);
				System.out.println(name);
				}
			}
			String ss = this.ui.getString("是否继续更改(y/n)");
			if(ss.equals("n")){
				this.greens();
			}
		}
		
	}

	//查询所有菜的方法
	private void selectGreens() {
		while(true){
			System.out.println("<<<查询菜谱");
			//直接显示所有菜
			System.out.println("类型id\t类型名称");
			List<FoodType> list = service.FindAllType();
			for (FoodType foodType : list) {
				System.out.println(foodType);
			}
			int i = this.ui.getInt("请输入要查询的菜的类型id");
			System.out.println("菜品id\t菜品名称\t菜品价格\t销售量\t类型id");
			List<Food> list2 = service.findFood(i);
			for (Food food : list2) {
				System.out.println(food);
			}
			String s = this.ui.getString("是否继续查询(y/n)");
			if(s.equals("n")){
				this.greens();
			}
		}
		
	}

	//菜类管理方法
	private void Vegetables() {
		while(true){
		this.v.welcom7();
		int select=this.ui.getInt("请选择：");
		if(select==0){
			this.v.println("返回");
			Manager();
		}else if(select==1){
			//添加菜类的方法
			this.addVegetables();
		}else if(select==2){
			//删除菜类的方法
			this.removeVegetables();
		}else if(select==3){
			//更改菜类的方法
			this.updateVegetables();
		}else if(select==4){
			//查询所有菜类的方法
			this.selectVegetables();
		}
		}
		
	}
	//添加菜类的方法
	private void addVegetables() {
		while(true){
			System.out.println("<<<添加菜类");
			String s = this.ui.getString("请输入添加的菜类名称:");
			String type = service.addFoodType(s);
			System.out.println(type);
			String ss = this.ui.getString("是否继续添加(y/n)");
			if(ss.equals("n")){
				this.Vegetables();
			}
		}
		
	}
	//删除菜类的方法
	private void removeVegetables() {
		while(true){
			System.out.println("<<<删除菜类");
			System.out.println("类型id\t类型名称");
			List<FoodType> list = service.FindAllType();
			for (FoodType foodType : list) {
				System.out.println(foodType);
			}
			int select=this.ui.getInt("请输入要删除的菜类id:");
			String type = service.delFoodType(select);
			System.out.println(type);
			String ss = this.ui.getString("是否继续删除(y/n)");
			if(ss.equals("n")){
				this.Vegetables();
			}
		}
		
	}
	//更改菜类的方法
	private void updateVegetables() {
		while(true){
			System.out.println("<<<更改菜类信息");
			System.out.println("类型id\t类型名称");
			List<FoodType> list = service.FindAllType();
			for (FoodType foodType : list) {
				System.out.println(foodType);
			}
			int select=this.ui.getInt("请输入要更改的菜类id:");
			String string = this.ui.getString("菜类名称更改为:");
			service.updateTypeName(select, string);
			String ss = this.ui.getString("是否继续更改(y/n)");
			if(ss.equals("n")){
				this.Vegetables();
			}
		}
		
	}
	//查询所有菜类的方法
	private void selectVegetables() {
		while(true){
			System.out.println("<<<显示菜类");
			//直接展示所有菜类
			System.out.println("类型id\t类型名称");
			List<FoodType> list = service.FindAllType();
			for (FoodType foodType : list) {
				System.out.println(foodType);
			}
			System.out.println("--------------------");
			this.Vegetables();
			
		}
		
	}

	//点菜，添加菜的方法
	private String addFood() {
		 while(true){
		this.v.welcome3();
		int select=this.ui.getInt("请选择：");
		if(select==0){
			this.v.println("返回");
			Employee();
		}else if(select==1){
			//具体查看菜类以及菜，进行选择
			System.out.println("<<<查看菜单点菜");
			this.selectFood();
		}else if(select==2){
			//购物车
			this.shopping();
		}else if(select==3){
			this.settleAccounts();
		}else{
			System.out.println("输入有误，请重新选择!");
		}
		}
		
	}
	//点菜后结账的方法
	private void settleAccounts() {
		System.out.println("<<<结账");
		double d = service.sumMoney();
		System.out.println("您需要付款:"+String.format("%.2f",d)+"元");
		String s=this.ui.getString("请问是否是会员(y/n)");
		if(s.equals("y")){
			while(true){
				int i =this.ui.getInt("请输入会员卡号:");
				if(service.ifCard(i)!=null){
					String i2=this.ui.getString("请输入卡号密码:");
					boolean ifCard = service.ifCard(i, i2);
					if(ifCard==true){
						System.out.println("应付:"+String.format("%.2f",d)+"元");
						Card card = service.ifCard(i);
						System.out.println("卡内余额为:"+String.format("%.2f",card.getCmoney())+"元");
						if(card.getCmoney()>=d){
							Order order = service.showOrder();
							Address a = order.getAddress();
							String address = service.FindAddressById(a.getAid());
							System.out.println("*******************************************");
							System.out.println(address+"----欢迎光临----");
							System.out.println("今天是："+order.getoDate());
							System.out.println("员工"+order.getEmp().getEid()+"为您服务");
							System.out.println("===============================");
							System.out.println("商品名称\t商品单价\t购买数量");
							List<Shopping> list = service.showShopping();
							for (Shopping shopping : list) {
								System.out.println(shopping);
							}
							double sumMoney = service.sumMoney();
							System.out.println("===============================");
							Card c = service.ifCard(i);
							double cDiscount = service.selectPower(c.getVip().getVid());
							//System.out.print("应收："+sumMoney);
							double f=sumMoney;
							System.out.println("应收："+String.format("%.2f", f));
							System.out.println("折扣为："+cDiscount);
							System.out.println("实收："+String.format("%.2f",sumMoney*cDiscount));
							double Ymoney=sumMoney*cDiscount;
							System.out.println("卡内余额："+String.format("%.2f",(card.getCmoney()-sumMoney*cDiscount)));
							String money = service.updateCardMoney(i,sumMoney*cDiscount, false);
							System.out.println("=============谢谢惠顾============");
							String j = this.ui.getString("是否满意本次服务(y/n)");
							if(j.equals("y")){
								service.updateAssess(select20, 1, true);
							}else{
								service.updateAssess(select20, 1, false);
							}
							System.out.println("感谢您的反馈！欢迎下次再来");
							PrintWriter pw=null;
							try {
								File file=new File("e:/yahui.xlsx");
								if(file.exists()){
									file.delete();
								}
								pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
								pw.println("*******************************");
								pw.println(address+"----欢迎光临----");
								pw.println("今天是："+order.getoDate());
								pw.println("员工"+order.getEmp().getEid()+"为您服务");
								pw.println("===============================");
								pw.println("商品名称\t商品单价\t购买数量");
								for (Shopping shopping : list) {
									pw.println(shopping);
								}
								pw.println("===============================");
								pw.println("应收："+String.format("%.2f", f));
								pw.println("折扣为："+cDiscount);
								pw.println("实收："+String.format("%.2f",sumMoney*cDiscount));
								System.out.println();
								pw.println("卡内余额："+String.format("%.2f",(card.getCmoney()-sumMoney*cDiscount)));
								pw.println("=============谢谢惠顾============");
								pw.println();
								pw.flush();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}finally{
								pw.close();
							}
							this.Employee();

						}//判断卡内余额是否足够
						else{
							System.out.println("卡内余额不足，请充值");
							this.rechargeCard();
						}
			}else{	
				System.out.println("密码不正确，请重新输入");
			}
				}
				System.out.println("卡号不存在");
				
	}
	}else{
		double sumMoney = service.sumMoney();
		System.out.println("总价为"+String.format("%.2f",sumMoney));
		double jin = this.ui.getDouble("请输入付款金额:");
		if(jin<d){
			System.out.println("您付款金额不够，请重新输入");
		}else{
			Order order = service.showOrder();
			Address a = order.getAddress();
			String address = service.FindAddressById(a.getAid());
			System.out.println("*******************************************");
			System.out.println(address+"----欢迎光临----");
			System.out.println("今天是："+order.getoDate());
			System.out.println("员工"+order.getEmp().getEid()+"为您服务");
			System.out.println("===============================");
			System.out.println("商品名称\t商品单价\t购买数量");
			List<Shopping> list = service.showShopping();
			for (Shopping shopping : list) {
				System.out.println(shopping);
			}
			
			System.out.println("===============================");
			//System.out.print("应收："+sumMoney);
			double f=sumMoney;
			System.out.println("应收："+String.format("%.2f", f));
			System.out.println("实收:"+jin);
			System.out.println("找零:"+String.format("%.2f",(jin-sumMoney)));
			System.out.println("=============谢谢惠顾============");
			PrintWriter pw=null;
			try {
				File file=new File("e:/yahui.xlsx");
				if(file.exists()){
					file.delete();
				}
				pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
				pw.println("*******************************");
				pw.println(address+"----欢迎光临----");
				pw.println("今天是："+order.getoDate());
				pw.println("员工"+order.getEmp().getEid()+"为您服务");
				pw.println("===============================");
				pw.println("商品名称\t商品单价\t购买数量");
				for (Shopping shopping : list) {
					pw.println(shopping);
				}
				pw.println("===============================");
				pw.println("应收："+String.format("%.2f", f));
				pw.println("实收："+String.format("%.2f",jin));
				pw.println("找零:"+String.format("%.2f",(jin-sumMoney)));
				pw.println("=============谢谢惠顾============");
				pw.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				pw.close();
			}
			
			String j = this.ui.getString("是否满意本次服务(y/n)");
			if(j.equals("y")){
				service.updateAssess(select20, 1, true);
			}else{
				service.updateAssess(select20, 1, false);
			}
			System.out.println("感谢您的反馈！欢迎下次再来");
			this.Employee();
		}
	}
	}
	//点菜进入购物车
	private void shopping() {
		while(true){
			System.out.println("<<<购物车");
			selectShopping();
			System.out.println("----------------------------");
			this.v.welcome4();
			int select=this.ui.getInt("请选择：");
			if(select==0){
				this.v.println("返回");
				addFood();
			}else if(select==1){
				//继续添加菜的方法
				addFood();
			}else if(select==2){
				//更改菜的信息的方法
				updateFood();
			}else if(select==3){
				//删除所选菜的方法
				removeFood();
			}else{
				System.out.println("输入有误，请重新选择!");
			}  
		}
		
	}
	//查看购物车所有选的内容
	private void selectShopping() {
		System.out.println("<<<查看购物车");
		System.out.println("菜品名称\t菜品价格\t数量");
		List<Shopping> list = service.showShopping();
		for (Shopping shopping : list) {
			System.out.println(shopping);
		}
	}

	//购物车中删除所选菜的方法，根据id删除
	private void removeFood() {
		System.out.println("<<<删除所选菜");
		String i=this.ui.getString("请输入要删除的菜名:");
		String food = service.deleteOneFood(i);
		String sss = this.ui.getString("是否继续删除菜(y/n)");
		if(sss.equals("n")){
			this.shopping();
		}
	}

	// 购物车中更改菜的信息的方法
	private void updateFood() {
		String i = this.ui.getString("请输入要更改菜的菜名:");
		int j = this.ui.getInt("请输入更改后菜的数量:");
		String string = service.updateFoodNum(i,j);
		System.out.println(string);
		String sss = this.ui.getString("是否继续更改菜的数量(y/n)");
		if(sss.equals("n")){
			this.shopping();
		}
	}

	//点菜之后先查看菜类，然后选择菜类。
	private void selectFood() {
		while(true){
			Emp emp = service.findbyId(select20);
			String string = service.FindAddressById(emp.getAddress().getAid());
			String order = service.addOrder(0.0, new Emp(select20), new Address(emp.getAddress().getAid(),string));
			while(true){
				System.out.println("查看菜类");
				System.out.println("菜类id\t菜类名");
				List<FoodType> findAllType = service.FindAllType();
				for (FoodType foodType : findAllType) {
					System.out.println(foodType);
				}
		a:		while(true){
					int select=this.ui.getInt("请选择：");
					
					List<Food> findFood = service.findFood(select);
					if(findFood!=null){
						System.out.println("菜品id\t菜品名称\t菜品价格\t销售量\t类型id");
						for (Food food : findFood) {
							System.out.println(food);
						}
						while(true){
							int select2=this.ui.getInt("请选择菜品id(按0返回):");
							if(select2==0){
								addFood();
							}
							Food food = service.findFoodById(select2);
							if(food!=null){
								int ss=this.ui.getInt("请输入数量:");
								String shop = service.addShop(food, ss);
								System.out.println(shop);
								service.updateFoodNum(select2, ss);
								String sss = this.ui.getString("是否继续添加(y/n)");
								if(sss.equals("n")){
								this.addFood();
								
								}
								break a;
							}
							System.out.println("菜品id不存在，请重新输入");
						}
						
						}
					System.out.println("菜类id不存在，请重新输入");
				}	
			}
			
								
		}	
		
	}

	//开卡的方法
	private void openCard() {
		String name = this.ui.getString("请输入用户姓名:");
		while(true){
			String password = this.ui.getString("请输入用户密码:");
			String password2 = this.ui.getString("请再次输入用户密码:");
			if(password.equals(password2)){
				double select=this.ui.getDouble("请输入充值金额:");
				if(select>=500){
					String card = service.addCard(name, password, 1, select);
					System.out.println(card);
					
				}else{
					String card2 = service.addCard(name, password, 0, select);
					System.out.println(card2);
				}
				List<Card> list = service.findAllCard();
				int cid=0;
				for (Card card : list) {
					if(card.getCid()>cid){
						cid=card.getCid();
					}
				}
				Card card = service.ifCard(cid);
				System.out.println("卡号\t \t用户名 \tVIP等级 \t余额");
				System.out.println(card);
				this.Employee();
			}
			System.out.println("密码不一致，请重新输入");
		}

	}
	//挂失卡的方法
	private void reportCard() {
			System.out.println("<<<挂失卡");
			int select=this.ui.getInt("请输入要挂失的卡id");
			String freezeCard = service.FreezeCard(select);
			System.out.println(freezeCard+"卡号不存在");
			this.Employee();
	}
	//补卡的方法
	private void reissueCard() {
		int select1=this.ui.getInt("请输入已挂失的卡号:");
		Card card2 = service.ifCopyCard(select1);
		if(card2==null){
			System.out.println("此卡并未挂失,请先去挂失");
			reportCard();
		}
		String select4=this.ui.getString("请输入用户名:");
		//判断用户和卡号是否是绑定的
		String own = service.AgainOwn(select1);
		while(true){
			String select2=this.ui.getString("请设置新会员卡的密码:");
			String select3=this.ui.getString("请再次输入密码:");
			//判断密码是否一致
			if(select2.equals(select3)){
				service.updateImgCard(select1, select4, select2);
				System.out.println(own);
				List<Card> list = service.findAllCard();
				int cid=0;
				for (Card card : list) {
					if(card.getCid()>cid){
						cid=card.getCid();
					}
				}
				Card card = service.ifCard(cid);
				System.out.println("卡号\t \t用户名 \tVIP等级 \t余额");
				System.out.println(card);
				this.Employee();
			}
			System.out.println("密码不一致，请重新输入");
		}
		
		
	}
	//给卡充值的方法
	private void rechargeCard() {
		System.out.println("特大喜讯！每月23号充值200返50！！！");
		SimpleDateFormat df = new SimpleDateFormat("dd");//设置日期格式
		String date=df.format(new Date());// new Date()为获取当前系统时间
		int select=this.ui.getInt("请输入要充值卡号:");
		System.out.println("卡号\t \t用户名 \tVIP等级 \t余额");
		System.out.println(service.ifCard(select));
		double select2=this.ui.getDouble("请输入要充值金额:");
		if(select2>=500){
			service.updateCardVip(select, 1);
			System.out.println("恭喜你成为超级会员！");
		}
		if(date.equals("23")){
			int a=(int)select2/200;
			String money = service.updateCardMoney(select, select2+a*50, true);
			System.out.println(money);
		}else{
			
			String money = service.updateCardMoney(select, select2, true);
			System.out.println(money);
		}
		System.out.println("卡号\t \t用户名 \tVIP等级 \t余额");
		System.out.println(service.ifCard(select));
		this.Employee();
	}
	//查询卡内余额的方法
	private void balanceCard() {
		int select=this.ui.getInt("请输入要查询卡号:");
		while(true){
			String passed=this.ui.getString("请输入卡密码:");
			boolean b = service.ifCard(select, passed);
			if(b){
				System.out.println("卡号\t \t用户名 \tVIP等级 \t余额");
				System.out.println(service.ifCard(select));
				this.Employee();
			}
			System.out.println("密码有误，请重新输入");
			
		}
		

	}
	//修改员工的个人信息
		private void modifyEmployee() {
			//可以修改姓名，性别，卡密码
			while(true){
				
				String select2=this.ui.getString("请输入修改后的姓名:");
				String select3=this.ui.getString("请输入修改后的性别:");
				String select4=this.ui.getString("请输入修改后的密码:");
				String updateImg = service.updateImg(select20, select2, select4, select3);
				System.out.println(updateImg);
				this.Employee();
				}
			
		}
}




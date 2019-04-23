package com.chinasofti.YaHui.control;

import java.util.List;

import com.chinasofti.YaHui.domain.Address;
import com.chinasofti.YaHui.domain.Card;
import com.chinasofti.YaHui.domain.Emp;
import com.chinasofti.YaHui.domain.Food;
import com.chinasofti.YaHui.domain.FoodType;
import com.chinasofti.YaHui.domain.Order;
import com.chinasofti.YaHui.domain.Shopping;
import com.chinasofti.YaHui.domain.VIP;

public interface YaHuiService {
	//*******************员工**************************
	//添加员工业务
	public String addEmp(String ename,String epasswd,String sex,int epower,Address a);
	//判断是普通员工还是经理
	public int ifEmpPower(int eid);
	//增加减少满意度业务
	public String updateAssess(int eid,int assess,boolean aif);
	//修改个人信息业务
	public String updateImg(int eid,String ename,String passwd,String sex);
	//修改员工地址业务
	public String updateAddress(int eid,Address a);
	//删除员工业务
	public String deleteEmp(int eid);
	//通过id查询员工
	public Emp findbyId(int eid);
	//查询所有员工
	public List<Emp> findAll();
	//判断id密码是否正确
	public boolean ifEmp(int eid,String passwd);
	//*********************会员卡************************
	//新建一张卡
	public String addCard(String name,String cpasswd,int vid,double cmoney);
	//删除一张卡
	public String deleteCard(int cid);
	//冻结一张卡
	public String FreezeCard(int cid);
	//补卡
	public String AgainOwn(int cid);
	//修改卡信息
	public String updateImgCard(int cid,String name,String passwd);
	//修改卡余额
	public String updateCardMoney(int cid,double money,boolean a);
	//修改卡VIP等级
	public String updateCardVip(int cid,int vid);
	//查询所有卡
	public List<Card> findAllCard();
	//查询卡号密码是否正确
	public boolean ifCard(int cid,String passed);
	//*********************vip等级************************
	//修改折扣
	public String update(double discount,int vid);
	//查询折扣
	public double selectPower(int vid);

	//查询所有
	public List<VIP> findAllVIP();
	//*********************菜品************************
	//通过种类id查询所有菜
	public List<Food> findFood(int typeid);
	//添加菜品
	public String addFood(String fname,double fprice,int typeid);
	//删除菜品
	public String delFood(int fid);
	//删除菜品根据类型
	public boolean delTypeFood(int typeid);
	//修改菜名
	public String updateFoodName(int fid,String fname);
	//修改价格
	public String updateFoodPrice(int fid,double price);
	//修改数量
	public String updateFoodNum(int fid,int num);
	//根据id查询菜
	public Food findFoodById(int id);
	//查询数量最多的菜
	public Food NnmMax();
	//*********************菜品种类************************
	//增加种类
	public String addFoodType(String ftname);
	//删除种类(删除其中的菜)
	public String delFoodType(int typeid);
	//修改种类
	public String updateTypeName(int typeid,String typename);
	//查询所有种类
	public List<FoodType> FindAllType();
	//*********************订单相关方法************************
	//添加一个订单
	public String addOrder(double oMonry, Emp emp, Address ads);
	//向订单中添加菜
	public String addShop(Food f,int fsnu);
	//删除一个菜
	public String deleteOneFood(String fname);
	//修改一个菜的数量
	public String updateFoodNum(String fname,int num);
	//计算总价
	public double sumMoney();
	//打印小票
	public void show(int cid,double money);
	//*********************订单相关方法************************
	//通过id查询地址
	public String FindAddressById(int aid);
	//显示所有
	public List<Address> FindAllAddress();
	//显示购物车
	public List<Shopping> showShopping();
	//查询卡
	public Card ifCard(int cid);
	//显示订单
	public Order showOrder();
	//查询复制卡
	public Card ifCopyCard(int cid);


}

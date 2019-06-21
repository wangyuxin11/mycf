//package com.github.mycf;
//
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
///**
// * 
// * `�����Ƽ�ϵͳ���������ݵ��Ƽ��㷨��Recommender System��Content-based Recommendation��
// * https://blog.csdn.net/qq_32690999/article/details/77434381
// * 
// * 
// * @author wangyx
// *
// */
//public class ItemCF {
//	
//	private OrderItemDao orderItemDao;
//	private int totalNum = 100;
//	private List<ProductType> productTypes;
//
//	
//	
//	/**
//	 * 1.��ʾ�û���Ϊ���󣬼�ͳ���û�����ĳ����Ʒ���͵�����
//	 * @param customer
//	 * @return
//	 */
//	public double[] getNumByCustomer(Customer customer) {
//		List<OrderItem> list = orderItemDao.findByCustomerAndAliveAndState(customer.getId(), 1, 2);
//		double[] vectore = new double[totalNum];
//		int index = 0;
//		for (ProductType type : productTypes) {
//			for (OrderItem orderItem : list) {
//				if (orderItem.getProduct().getProductType().id == type.id) {
//					vectore[index] = vectore[index] + orderItem.getNum();
//				}
//			}
//		}
//		return vectore;
//	}
//	/**
//	 * 2.�����Ҿ������ÿ���û��������û�����Ϊ���ƶ�
//	 *   ������������������û�֮������ƶȣ����б����Ϳ��Ի�ȡȫ�����ƶ�
//	 * @param a
//	 * @param b
//	 * @return
//	 */
//	public double countSimilarity(double[] a, double[] b) {
//		double total = 0;
//		double alength = 0;
//		double blength = 0;
//		for (int i = 0; i < a.length; i++) {
//			total = total + a[i] * b[i];
//			alength = alength + a[i] * a[i];
//			blength = blength + b[i] * b[i];
//		}
//
//		double down = Math.sqrt(alength) * Math.sqrt(blength);
//		double result = 0;
//		if (down != 0) {
//			result = total / down;
//		}
//
//		return result;
//	}
//	
//	
//	/**
//	 * 3.ȡ���ƶ���ߵ�ǰn���û�����������û����ϣ���Map��ֵ��������
//	 * @param customer
//	 * @return
//	 */
//	public List<Map.Entry<Long, Double>> getMaxSimilarity(Customer customer) {
//		Map<Long, Double> result = new HashMap<Long, Double>();
//		double vector[] = (double[]) users.get(customer.getId());
//		for (Map.Entry<Long, Object> entry : users.entrySet()) {
//			if (entry.getKey() != customer.getId()) {
//				double[] temp = (double[]) entry.getValue();
//				double similarity = countSimilarity(temp, vector);
//				result.put(entry.getKey(), similarity);
//			}
//		}
//		List<Map.Entry<Long, Double>> list = new LinkedList<Map.Entry<Long, Double>>(result.entrySet());
//		Collections.sort(list, new Comparator<Map.Entry<Long, Double>>() {
//			public int compare(Map.Entry<Long, Double> o1, Map.Entry<Long, Double> o2) {
//				return (o2.getValue()).compareTo(o1.getValue());
//			}
//		});
//		return list;
//	}
//
//	
//	/**
//	 * 4.��������û����Ϲ������Ʒ����ͳ�������û��������Ʒ����������������
//	 * @param list
//	 * @return
//	 */
//	public Map<Long, ProductNumModel> getProducts(List<Map.Entry<Long, Double>> list) {
//		List<Customer> simCustomers = new ArrayList<Customer>();
//		System.out.println("���ƶȸߵ�3���û�  ");
//		for (int i = 0; i < list.size() && i < 3; i++) {
//			Long id = list.get(i).getKey();
//			Customer customer = customerDao.findByIdAndAlive(id, 1);
//			simCustomers.add(customer);
//		}
//		Map<Long, ProductNumModel> map = new HashMap<Long, ProductNumModel>();
//		for (Customer customer : simCustomers) {
//			Map<Long, ProductNumModel> hashSet = getCustomerProduct(customer);
//			for (Map.Entry<Long, ProductNumModel> entry : hashSet.entrySet()) {
//				ProductNumModel model = null;
//				if (map.containsKey(entry.getKey())) {
//					model = map.get(entry.getKey());
//					model.num += entry.getValue().num;
//				} else {
//					model = new ProductNumModel();
//					model.product = entry.getValue().product;
//					model.num = entry.getValue().num;
//				}
//				map.put(entry.getKey(), model);
//			}
//		}
//		return map;
//	}
//
//}
//
//
//class Customer{
//	private int id;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//}
//
//class ProductType{
//	public int id;
//}
//
//class Product{
//	private ProductType productType;
//
//	public ProductType getProductType() {
//		return productType;
//	}
//
//	public void setProductType(ProductType productType) {
//		this.productType = productType;
//	}
//}
//
//class OrderItem{
//	private Product product;
//	private Integer num;
//
//	public Product getProduct() {
//		return product;
//	}
//	public void setProduct(Product product) {
//		this.product = product;
//	}
//	public Integer getNum() {
//		return num;
//	}
//	public void setNum(Integer num) {
//		this.num = num;
//	}
//}
//
//
//
//interface OrderItemDao{
//	List<OrderItem> findByCustomerAndAliveAndState(int id, int i, int j);	
//}
//

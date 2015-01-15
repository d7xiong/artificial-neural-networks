package xu.main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 单层感知器
 * 
 * @author xu
 * 
 */
public class SinglePerceptron {

	List<Integer[]> dataList = null;
	Double[] weightArray = null;
	double bias = 0.5; // 偏置
	double s = 0.5; // 学习率

	int maxTrainNum = 1000; // 最大训练次数

	public static void main(String[] args) {

		SinglePerceptron singlePerceptron = new SinglePerceptron();
		singlePerceptron.train();

		List<Integer> inputList = new ArrayList<Integer>();
		inputList.add(0);
		inputList.add(0);
		inputList.add(1);

		
		singlePerceptron.printWeight();
		System.out.println(singlePerceptron.currentTarget(inputList));

	}

	public SinglePerceptron() {
		dataList = new ArrayList<Integer[]>();
		this.createTrainData();
		weightArray = new Double[dataList.size()];
		this.createRandomWeight();
	}

	public void printWeight() {
		for (Double weight : weightArray) {
			System.out.print(weight + "\t");
		}
		System.out.println();
	}

	/**
	 * 感知器训练
	 */
	public void train() {
		int trainNum = 0;
		while (true) {
			trainNum++;
			for (int dataIndex = 0, len = dataList.size(); dataIndex < len; dataIndex++) {
				List<Integer> inputDataList = this.extractInputData(dataIndex);
				int currentTarget = this.currentTarget(inputDataList);
				for (int weightIndex = 0, weigthLen = weightArray.length; weightIndex < weigthLen; weightIndex++) {
					weightArray[weightIndex] = weightArray[weightIndex] + s * (this.dataList.get(dataList.size() - 1)[weightIndex] - currentTarget) * inputDataList.get(weightIndex);
				}
			}
			if (trainNum == maxTrainNum) {
				break;
			}
		}
	}

	public List<Integer> extractInputData(int dataIndex) {
		List<Integer> resultDataList = new ArrayList<Integer>();

		for (Integer[] inputArray : this.dataList) {
			resultDataList.add(inputArray[dataIndex]);
		}

		return resultDataList;
	}

	/**
	 * 计算当前模型输入值的结果
	 * 
	 * @param inputList
	 * @return
	 */
	public int currentTarget(List<Integer> inputList) {
		double result = 0.0;

		for (int inputIndex = 0, len = inputList.size(); inputIndex < len; inputIndex++) {
			result += inputList.get(inputIndex) * this.weightArray[inputIndex];
		}
		result += bias;
		return sign(result);
	}

	/**
	 * 符号函数
	 * 
	 * @param input
	 * @return
	 */
	public int sign(double input) {
		if (input > 0) {
			return 1;
		}
		return 0;
	}

	/**
	 * 随机初始化权值向量
	 * 
	 * @param weightList
	 * @param dataListSize
	 */
	public void createRandomWeight() {
		for (int weightIndex = 0; weightIndex < dataList.size(); weightIndex++) {
			weightArray[weightIndex] = new Random().nextDouble() * 10;
		}
	}

	/**
	 * 创建训练数据
	 * 
	 */
	public void createTrainData() {

		Integer[] input1 = { 1, 1, 1, 1, 0, 0, 0, 0 };
		Integer[] input2 = { 0, 0, 1, 1, 0, 1, 1, 0 };
		Integer[] input3 = { 0, 1, 0, 1, 1, 0, 1, 0 };

		Integer[] target = { -1, 1, 1, 1, -1, -1, 1, -1 };

		dataList.add(input1);
		dataList.add(input2);
		dataList.add(input3);

		dataList.add(target);
	}

}

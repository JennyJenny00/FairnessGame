package cs630;

import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.AttributeStats;
import weka.core.Attribute;
import weka.experiment.Stats;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class loadSavedData {
	public static void main(String args[]) throws Exception{
		DataSource source = new DataSource("resources/communitiesdataNoMissing.arff");
		//System.out.println(dataset);
		//get instances object 
			Instances data = source.getDataSet();
			//set class index .. as the last attribute
			 if (data.classIndex() == -1) {
			     data.setClassIndex(data.numAttributes() - 1);
			 }
			 //get number of attributes (notice class is not counted)
				int numAttr = data.numAttributes() - 1;
				//get ViolentCrimesPerPop
				System.out.println(data.attribute(numAttr).name());
				//get number of instances
				int numInst = data.numInstances();
				System.out.println(numInst);
				//loop through all instances
				for (int j = 0; j < numInst; j++) {
					//get the j'th instance
					Instance instance = data.instance(j);
					//check if 1st attr is missing from the j'th instance
					if (instance.value(numAttr)>=0.7) {
						instance.setValue(numAttr, 1.00);
						}
					else {
						instance.setValue(numAttr, 0.00);
					}
				}
		
		ArffSaver saver = new ArffSaver();
		saver.setInstances(data);
		saver.setFile(new File("resources/new.arff"));
		saver.writeBatch();
		
	}

}

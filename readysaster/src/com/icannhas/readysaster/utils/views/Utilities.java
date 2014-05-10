package com.icannhas.readysaster.utils.views;

public class Utilities {
	
	public static final String[] buildingMaterial = new String[] {"WOOD","HYBRID","MASONRY","CONCRETE","STEEL"};
	
	public static String[] getStructureTypeList(String buildingMat){
		if(buildingMat.equals("WOOD")){
			return new String[] {"Wood, light frame","Bamboo"};
		}
		if(buildingMat.equals("HYBRID")){
			return new String[] {"Makeshift"};
		}
		if(buildingMat.equals("MASONRY")){
			return new String[] {"Concrete hollow blocks with wood or light metal","Concrete hollow blocks","Adobe","Unreinforced masonry bearing walls"};
		}
		if(buildingMat.equals("CONCRETE")){
			return new String[] {"Reinforced concrete moment frames with wood or light metal","Reinforced concrete moment frames","Concrete shear walls and frames","Precast Frame"};
		}
		else{
			return new String[] {"Steel moment frame","Light metal frame","Steel frame with cast-In-place concrete shear walls"};
		}
	}

}

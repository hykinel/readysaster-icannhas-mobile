package com.icannhas.readysaster;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * Hello world!
 * 
 */
public class ReadysasterDaoGenerator {
	public static void main(String[] args) throws IOException, Exception {
		Schema schema = new Schema(2, "com.icannhas.readysaster");
		schema.enableKeepSectionsByDefault();

		addEntities(schema);

		new DaoGenerator().generateAll(schema, "../readysaster/src-gen");
	}

	private static void addEntities(Schema schema) {
		Entity data = schema.addEntity("Data");
		data.addIdProperty();

		Entity personal = schema.addEntity("PersonalDetails");
		personal.addIdProperty();
		personal.addStringProperty("name");
		personal.addLongProperty("contact_number");

		Entity location = schema.addEntity("LocationDetails");
		location.addIdProperty();
		location.addDoubleProperty("lat");
		location.addDoubleProperty("lng");
		location.addStringProperty("photoFile");

		Entity structure = schema.addEntity("StructureDetails");
		structure.addIdProperty();
		structure.addIntProperty("house_size");
		structure.addStringProperty("building_material");
		structure.addStringProperty("structure_type");
		structure.addIntProperty("number_storey");

		// 1:1 relationships for Data 'fields'
		Property personalIdProperty = data.addLongProperty("personal_id").getProperty();
		data.addToOne(personal, personalIdProperty);
		Property locationIdProperty = data.addLongProperty("location_id").getProperty();
		data.addToOne(location, locationIdProperty);
		Property structureIdProperty = data.addLongProperty("structure_id").getProperty();
		data.addToOne(structure, structureIdProperty);

	}
}

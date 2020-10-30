package creator;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ListConverter implements AttributeConverter<List<GameCoreData>, String> {

	// Converting from List type object to String
	@Override
	public String convertToDatabaseColumn(List<GameCoreData> solutionsDefinitions) {
		String attributeJson = null;
		try {
			attributeJson = new ObjectMapper().writeValueAsString(solutionsDefinitions);
		} catch (final JsonProcessingException e) {
			e.printStackTrace();
		}
		return attributeJson;
	}

	// Converting from String to List type object
	@Override
	public List<GameCoreData> convertToEntityAttribute(String solutionsDefinitionsJson) {
		List<GameCoreData> solutionsDefinitions = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			solutionsDefinitions = objectMapper.readValue(solutionsDefinitionsJson, List.class);
		} catch (final JsonProcessingException e) {
			e.printStackTrace();
		}
		return solutionsDefinitions;
	}
	
}

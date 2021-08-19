import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) throws JsonProcessingException {
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

        //System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json); //JsonNode로 하나씩 파싱시켜 가져옴
        String _name = jsonNode.get("name").asText(); //이미 변수 타입을 알고 있을때 사용 가능 , 직접 노드를 건드릴 때 사용
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : " + _name);
        System.out.println("age : " + _age);

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars; //ArrayNode로 파싱해서 강제 타입 변환시켜 줌

        //ArrayNode를 원하는 클래스로 바꿈
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        //convertValue는 object를 가지고 json이 아닌 원하는 클래스로 매핑을 시켜줌
        //TypeReference에는 받고자 하는 Generic type을 넣어 줌.

        System.out.println(_cars);

        ObjectNode objectNode = (ObjectNode)jsonNode; //특정 json의 값을 바꿈
        objectNode.put("name", "steve"); //put은 set과 같은 기능
        objectNode.put("age", 20);

        System.out.println(objectNode.toPrettyString());
    }
}

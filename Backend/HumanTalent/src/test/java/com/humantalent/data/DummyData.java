package com.humantalent.data;

import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;
import com.humantalent.domain.model.person.PersonCountry;
import com.humantalent.domain.model.person.PersonIdType;

public class DummyData {
    public static Person employee01() {
        return new Employee(null,"Juan","Carlos","Perez","Duran", PersonCountry.CO, PersonIdType.CITIZENSHIP_CARD,
                "1033456654");
    }

    public static Person employee02() {
        return new Employee(null,"Juan","Camilo","Perez","Juarez", PersonCountry.CO, PersonIdType.CITIZENSHIP_CARD,
                "1033763321");
    }
}

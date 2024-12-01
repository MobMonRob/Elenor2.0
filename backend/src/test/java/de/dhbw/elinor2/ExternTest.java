package de.dhbw.elinor2;

import de.dhbw.elinor2.entities.Extern;
import de.dhbw.elinor2.repositories.ExternRepository;
import de.dhbw.elinor2.utils.GenericTest;
import de.dhbw.elinor2.utils.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ExternTest extends GenericTest<Extern, Extern, UUID>
{
    @Autowired
    private ExternRepository externRepository;

    private String BASE_URL = "http://localhost:8080/api/externs";


    @Override
    public String getObjectAssertionIdentificationSavedEntity(Extern extern)
    {
        return extern.getName();
    }

    @Override
    public String getObjectAssertionIdentificationReceivedEntity(Extern extern)
    {
        return getObjectAssertionIdentificationSavedEntity(extern);
    }

    @Override
    public TestObject<Extern, Extern, UUID> initTestObject()
    {
        TestObject<Extern, Extern, UUID> testObject = new TestObject<>();
        testObject.setRepository(externRepository);
        testObject.setBaseUrl(BASE_URL);
        testObject.setEntityClass(Extern.class);
        testObject.setEntityArrayClass(Extern[].class);

        Extern extern = new Extern();
        extern.setName("testName");
        extern = externRepository.save(extern);

        testObject.setInitSavedEntity(extern);
        testObject.setInitSavedEntityId(extern.getId());

        Extern updated = new Extern();
        updated.setName("updatedName");
        updated.setId(extern.getId());

        testObject.setUpdateEntity(updated);

        Extern newExtern = new Extern();
        newExtern.setName("newName");

        testObject.setNewEntity(newExtern);

        return testObject;
    }
}

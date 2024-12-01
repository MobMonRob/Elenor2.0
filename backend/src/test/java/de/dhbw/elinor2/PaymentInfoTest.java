package de.dhbw.elinor2;

import de.dhbw.elinor2.entities.PaymentInfo;
import de.dhbw.elinor2.repositories.PaymentInfoRepository;
import de.dhbw.elinor2.utils.GenericTest;
import de.dhbw.elinor2.utils.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PaymentInfoTest extends GenericTest<PaymentInfo, PaymentInfo, UUID>
{
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    private final String BASE_URL = "http://localhost:8080/api/paymentinfos";

    @Override
    public String getObjectAssertionIdentificationSavedEntity(PaymentInfo paymentInfo)
    {
        return paymentInfo.getName();
    }

    @Override
    public String getObjectAssertionIdentificationReceivedEntity(PaymentInfo paymentInfo)
    {
        return getObjectAssertionIdentificationSavedEntity(paymentInfo);
    }


    @Override
    public TestObject<PaymentInfo, PaymentInfo, UUID> initTestObject()
    {
        TestObject<PaymentInfo, PaymentInfo, UUID> testObject = new TestObject<>();
        testObject.setBaseUrl(BASE_URL);
        testObject.setRepository(paymentInfoRepository);
        testObject.setEntityClass(PaymentInfo.class);
        testObject.setEntityArrayClass(PaymentInfo[].class);

        PaymentInfo initPaymentInfo = new PaymentInfo();
        initPaymentInfo.setName("testName");
        initPaymentInfo = paymentInfoRepository.save(initPaymentInfo);
        testObject.setInitSavedEntityId(initPaymentInfo.getId());
        testObject.setInitSavedEntity(initPaymentInfo);

        PaymentInfo updatePaymentInfo = new PaymentInfo();
        updatePaymentInfo.setName("updatedName");
        updatePaymentInfo.setId(initPaymentInfo.getId());
        testObject.setUpdateEntity(updatePaymentInfo);

        PaymentInfo newPaymentInfo = new PaymentInfo();
        newPaymentInfo.setName("newTestName");
        testObject.setNewEntity(newPaymentInfo);

        return testObject;
    }
}

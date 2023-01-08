package ir.mapsa.wallet.baseServiceTest;

import ir.mapsa.wallet.WalletApplication;
import ir.mapsa.wallet.entities.Account;
import ir.mapsa.wallet.exceptions.BaseException;
import ir.mapsa.wallet.exceptions.IdNullException;
import ir.mapsa.wallet.exceptions.NotFoundExceptions;
import ir.mapsa.wallet.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletApplication.class)
public class BaseService {

    @Autowired
    private AccountService accountService;

    @DisplayName("test save method for account")
    @Test
    public void test_Save_Method() throws BaseException {
        var account= Account.builder()
                .firstName("ali")
                .lastName("bagheri")
                .username("myuser")
                .password("mypass")
                .build();
        Account accountEntity=accountService.saveOrUpdate(account);
        assertThat(accountEntity).isNotNull();
    }
    @DisplayName("test save method for null value")
    @Test
    public void test_Save_Method_When_Id_Is_Null(){
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                ()->accountService.saveOrUpdate(null));
    }
    @DisplayName("test update method for account")
    @Test
    public void test_Update_Method() throws BaseException {
        var account= Account.builder()
                .firstName("ali")
                .lastName("bagheri")
                .username("myuser")
                .password("mypass")
                .build();
        Account accountEntity=accountService.saveOrUpdate(account);
        Account account1 = accountService.saveOrUpdate(accountEntity);
        Assertions.assertTrue(account1.getId() == accountEntity.getId());

    }
    @Test
    public void test_Throw_Not_Found_Exception() {
        Assertions.assertThrows(NotFoundExceptions.class,
                () -> accountService.findById(200L));
    }

    @Test
    public void test_Throw_Id_Null_Exception() {
        Assertions.assertThrows(IdNullException.class,
                () -> accountService.findById(null));
    }


    @Test
    public void test_Return_Entity_By_UsernameAndPassword() throws BaseException {
        var account = Account.builder()
                .username("hedaiat")
                .password("s22s")
                .build();
        Account accountEntity = accountService.saveOrUpdate(account);
        Assertions.assertAll(
                () -> Assertions.assertEquals(accountEntity.getUsername(), "hedaiat"),
                () -> Assertions.assertEquals(accountEntity.getPassword(), "s22s")
        );
    }


}

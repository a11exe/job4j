package bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.02.2019
 */
public class BankTest {

    @Test
    public void whenAddOneUserThenEmptyThisUserAccounts() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        bank.addUser(user1);
        assertThat(bank.getUserAccounts("34567891F").size(), is(0));
    }

    @Test
    public void whenAddThreeUserSamePassportThenOnlyOneAdded() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        User user2 = new User("Boris", "34567891F");
        User user3 = new User("Petr", "34567891F");
        User user4 = new User("Bill", "34567891F");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addUser(user3);
        bank.deleteUser(user4);
        assertNull(bank.getUserAccounts("34567891F"));
    }

    @Test
    public void whenDeleteUserThenNoAccounts() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        bank.addUser(user1);
        bank.deleteUser(user1);
        assertNull(bank.getUserAccounts("34567891F"));
    }

    @Test
    public void whenAddAccountToUserThenUserHasThisAccount() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        bank.addUser(user1);
        Account account = new Account(100.67, "7888897");
        bank.addAccountToUser("34567891F", account);
        assertThat(bank.getUserAccounts("34567891F").get(0), is(account));
    }

    @Test
    public void whenAddUserSamePasswordThenUserAccountSame() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        bank.addUser(user1);
        Account account = new Account(100.67, "7888897");
        bank.addAccountToUser("34567891F", account);
        User user2 = new User("Bob", "34567891F");
        bank.addUser(user2);
        assertThat(bank.getUserAccounts("34567891F").get(0), is(account));
    }


    @Test
    public void whenDeleteAccountFromUserThenUserHasNoAccounts() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        bank.addUser(user1);
        Account account = new Account(100.67, "7888897");
        bank.addAccountToUser("34567891F", account);
        bank.deleteAccountFromUser("34567891F", account);
        assertThat(bank.getUserAccounts("34567891F").size(), is(0));
    }

    @Test
    public void whenAddUserThreeAccountsThenUserHasThisTreeAccounts() {
        List<Account> accounts = List.of(
        new Account(100.3, "7878787"),
        new Account(500.3, "7878888"),
        new Account(800, "7878999"));
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        bank.addUser(user1);
        accounts.forEach(s->bank.addAccountToUser("34567891F", s));
        assertThat(bank.getUserAccounts("34567891F"), is(accounts));
    }

    @Test
    public void whenAddUserThreeAccountsDeleteOneThenUserHasTwoAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(100.3, "7878787"));
        Account account2 = new Account(500.3, "7878888");
        accounts.add(account2);
        accounts.add(new Account(800, "7878999"));
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        bank.addUser(user1);
        accounts.forEach(s->bank.addAccountToUser("34567891F", s));
        bank.deleteAccountFromUser("34567891F", account2);
        accounts.remove(account2);
        assertThat(bank.getUserAccounts("34567891F"), is(accounts));
    }

    @Test
    public void whenDeleteAllAccountsThenUserHasNoAccounts() {
        Account account1 = new Account(100.3, "7878787");
        Account account2 = new Account(500.3, "7878888");
        Account account3 = new Account(800, "7878999");
        List<Account> accounts = List.of(account1, account2, account3);
        Bank bank = new Bank();
        User user1 = new User("Ivan", "34567891F");
        bank.addUser(user1);
        accounts.forEach(s->bank.addAccountToUser("34567891F", s));

        bank.deleteAccountFromUser("34567891F", account1);
        bank.deleteAccountFromUser("34567891F", account2);
        bank.deleteAccountFromUser("34567891F", account3);

        assertThat(bank.getUserAccounts("34567891F").size(), is(0));
    }

    @Test
    public void whenNoUserThenAccountsIsNull() {
        Bank bank = new Bank();
        assertNull(bank.getUserAccounts("34567891F"));
    }

    @Test
    public void whenNoUsersThenNoTransferMoney() {
        Bank bank = new Bank();
        assertFalse(bank.transferMoney("asas", "79797", "sadasd", "79797", 100));
    }

    @Test
    public void whenNoSrcUserThenNoTransferMoney() {
        Bank bank = new Bank();
        User userDest = new User("Ivan", "4454545");
        Account accountDest = new Account(400.45, "78797979");
        bank.addUser(userDest);
        bank.addAccountToUser("4454545", accountDest);
        assertFalse(bank.transferMoney("asas", "79797", "4454545", "78797979", 100));
    }

    @Test
    public void whenNoDestUserThenNoTransferMoney() {
        Bank bank = new Bank();
        User userSource = new User("Ivan", "4454545");
        Account accountSource = new Account(400.45, "78797979");
        bank.addUser(userSource);
        bank.addAccountToUser("4454545", accountSource);
        assertFalse(bank.transferMoney("4454545", "78797979", "56456", "34536", 100));
    }

    @Test
    public void whenNotEnoughMoneyThenNoTransferMoney() {
        Bank bank = new Bank();
        User userSource = new User("Bob", "78979797");
        Account accountSource = new Account(400.45, "888");
        bank.addUser(userSource);
        User userDest = new User("Ivan", "4454545");
        Account accountDest = new Account(400.45, "78797979");
        bank.addUser(userDest);
        bank.addAccountToUser("4454545", accountDest);
        bank.addAccountToUser("78979797", accountSource);
        assertFalse(bank.transferMoney("78979797", "888", "4454545", "78797979", 800));
    }

    @Test
    public void whenEnoughMoneyThenTransferMoneyOk() {
        Bank bank = new Bank();
        User userDest = new User("Ivan", "4454545");
        Account accountDest = new Account(100.45, "8888");
        bank.addUser(userDest);
        bank.addAccountToUser("4454545", accountDest);
        User userSource = new User("Bob", "78979797");
        Account accountSource = new Account(800.45, "78797979");
        bank.addUser(userSource);
        bank.addAccountToUser("78979797", accountSource);
        assertTrue(bank.transferMoney("78979797", "78797979", "4454545", "8888", 800));
    }
}
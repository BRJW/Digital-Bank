package io.demo.bank.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import io.demo.bank.model.AccountStanding;
import io.demo.bank.model.AccountType;
import io.demo.bank.model.OwnershipType;
import io.demo.bank.model.TransactionState;
import io.demo.bank.model.TransactionType;
import io.demo.bank.model.security.Role;
import io.demo.bank.repository.AccountStandingRepository;
import io.demo.bank.repository.AccountTypeRepository;
import io.demo.bank.repository.OwnershipTypeRepository;
import io.demo.bank.repository.RoleRepository;
import io.demo.bank.repository.TransactionStateRepository;
import io.demo.bank.repository.TransactionTypeRepository;

@Component
public class DefaultData implements CommandLineRunner, Ordered {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultData.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AccountTypeRepository accountTypeRepository;
	
	@Autowired
	private OwnershipTypeRepository ownershipTypeRepository;
	
	@Autowired
	private AccountStandingRepository accountStandingRepository;
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	@Autowired
	private TransactionStateRepository transactionStateRepository;
	
	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public void run(String... args) throws Exception {
		
		LOG.info("*********************************");
		LOG.info("***** Checking Default Data *****");
		
		// Load Roles if they do not exist
		if (roleRepository.findByName("ROLE_USER") == null) {
			
			LOG.info("** Loading Roles...");
			
			List<Role> roles = new ArrayList<Role>();
			
			roles.add(new Role("ROLE_USER"));
			roles.add(new Role("ROLE_ADMIN"));
			roles.add(new Role("ROLE_API"));
			
			roleRepository.saveAll(roles);
			
		}
		
		// Load Account Types if they do not exist
		if (accountTypeRepository.findByCode("SCK") == null) {
			
			LOG.info("** Loading Account Types...");
			
			List<AccountType> accountTypes = new ArrayList<AccountType>();
			
			accountTypes.add(new AccountType("SCK", "CHK", "Standard Checking", 0.00, new BigDecimal(25.00), new BigDecimal(25.00), new BigDecimal(10.00)));
			accountTypes.add(new AccountType("ICK", "CHK", "Interest Checking", 0.50, new BigDecimal(25.00), new BigDecimal(25.00), new BigDecimal(10.00)));
			accountTypes.add(new AccountType("SAV", "SAV", "Savings", 1.85, new BigDecimal(25.00), new BigDecimal(25.00), new BigDecimal(10.00)));
			accountTypes.add(new AccountType("MMA", "SAV", "Money Market", 2.02, new BigDecimal(2500.00), new BigDecimal(25.00), new BigDecimal(10.00)));
			accountTypes.add(new AccountType("CDA", "INV", "Certificate of Deposit", 3.25, new BigDecimal(2000.00), new BigDecimal(0.00), new BigDecimal(0.00)));
			accountTypes.add(new AccountType("IRA", "INV", "Individual Retirement", 2.68, new BigDecimal(1000.00), new BigDecimal(0.00), new BigDecimal(0.00)));
			accountTypes.add(new AccountType("AUT", "LON", "Auto Loan", 4.21, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00)));
			accountTypes.add(new AccountType("MRG", "LON", "Mortgage Loan", 4.70, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00)));
			accountTypes.add(new AccountType("CRD", "CCA", "Credit Card", 16.92, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00)));
			
			accountTypeRepository.saveAll(accountTypes);			
		}
		
		// Load Ownership Types if they do not exist
		if (ownershipTypeRepository.findByCode("IND") == null) {
			
			LOG.info("** Loading Ownership Types...");
			
			List<OwnershipType> ownershipTypes = new ArrayList<OwnershipType>();
			
			ownershipTypes.add(new OwnershipType("IND", "Individual"));
			ownershipTypes.add(new OwnershipType("JNT", "Joint"));
		
			ownershipTypeRepository.saveAll(ownershipTypes);
			
		}
		
		// Load Account Standing Types if they do not exist
		if (accountStandingRepository.findByCode("A1") == null) {
			
			LOG.info("** Loading Account Standings...");
			
			List<AccountStanding> acccountStandings = new ArrayList<AccountStanding>();
			
			acccountStandings.add(new AccountStanding ("A1", "Open"));
			acccountStandings.add(new AccountStanding ("A2", "Paid"));
			acccountStandings.add(new AccountStanding ("A3", "Closed"));
			acccountStandings.add(new AccountStanding ("A4", "Inactive"));
			acccountStandings.add(new AccountStanding ("21", "Deceased"));
			acccountStandings.add(new AccountStanding ("97", "Charge Off"));
			acccountStandings.add(new AccountStanding ("94", "Foreclosed"));
			acccountStandings.add(new AccountStanding ("78", "Past Due 60 Days"));
			acccountStandings.add(new AccountStanding ("80", "Past Due 90 Days"));
			acccountStandings.add(new AccountStanding ("82", "Past Due 120 Days"));
			acccountStandings.add(new AccountStanding ("95", "Past Due 150+ Days"));
				
			accountStandingRepository.saveAll(acccountStandings);			
		}
		
		// Load Transaction Types if they do not exist
		if (transactionTypeRepository.findByCode("ATM") == null) {
			
			LOG.info("** Loading Transaction Types...");
			
			List<TransactionType> transactionTypes = new ArrayList<TransactionType>();
			
			transactionTypes.add(new TransactionType("ATM", "ATM"));
			transactionTypes.add(new TransactionType("DBT", "Debit"));
			transactionTypes.add(new TransactionType("CRG", "Charge"));
			transactionTypes.add(new TransactionType("CHK", "Check"));
			transactionTypes.add(new TransactionType("DPT", "Deposit"));
			transactionTypes.add(new TransactionType("POS", "Point of Sale"));
			transactionTypes.add(new TransactionType("TRN", "Transfer"));
			transactionTypes.add(new TransactionType("WTH", "Withdrawl"));
			transactionTypes.add(new TransactionType("INT", "Interest Income"));
			transactionTypes.add(new TransactionType("DIV", "Dividend Credit"));
			transactionTypes.add(new TransactionType("PMT", "Payment"));
			transactionTypes.add(new TransactionType("OVD", "Overdraft"));
			transactionTypes.add(new TransactionType("FEE", "Fee"));
			transactionTypes.add(new TransactionType("LTF", "Late Fee"));
			transactionTypes.add(new TransactionType("OVF", "Overdraft Fee"));
			transactionTypes.add(new TransactionType("COF", "Check Order Fee"));
			transactionTypes.add(new TransactionType("TNF", "Transfer Fee"));
			transactionTypes.add(new TransactionType("DDP", "Direct Deposit"));
			transactionTypes.add(new TransactionType("EFT", "Electronic Funds Transfer"));
			transactionTypes.add(new TransactionType("RFD", "Refund"));
			
			transactionTypeRepository.saveAll(transactionTypes);
		}
		
		// Load Transaction States if they do not exist
		if (transactionStateRepository.findByCode("ERR") == null) {
			
			LOG.info("** Loading Transaction States...");
			
			List<TransactionState> transactionStates = new ArrayList<TransactionState>();
			
			transactionStates.add(new TransactionState("ERR", "Error"));
			transactionStates.add(new TransactionState("PND", "Pending"));
			transactionStates.add(new TransactionState("COM", "Complete"));
			transactionStates.add(new TransactionState("REV", "Review"));
			
			transactionStateRepository.saveAll(transactionStates);
		}
		

		LOG.info("*********************************");
	}

}

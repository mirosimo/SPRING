import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mirosimo.car_showroom.model.Customer;
import com.mirosimo.car_showroom.repository.CustomerRepository;



@Service
public class CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	
	public void saveCustomer(Customer customer) {
		this.customerRepository.save(customer);
	}
	
	public Customer getCustomerById(long id) {
		Optional<Customer> optional = this.customerRepository.findById(id);
		Customer customer = null;
		if (optional.isPresent()) {
			customer = optional.get();
		} else {
			throw new RuntimeException(" Not found customer ID: " + id);
		}
		return customer;
	}
	
	public void deleteCustomerById(long id) {
		this.customerRepository.deleteById(id);
	}
	
	public Page<Customer> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return this.customerRepository.findAll(pageable);
	}
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mirosimo.car_showroom.model.Customer;
import com.mirosimo.car_showroom.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "index";		
	}
	
	@GetMapping("/newCustomerForm")
	public String newCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer_new";
	} 
	
	@PostMapping("/saveCustomer")
	public String savaCustomer(@ModelAttribute("customer") Customer customer) {
		this.customerService.saveCustomer(customer);
		return "redirect:/";
	}
	
	@GetMapping("/updateForm/{id}")
	public String updateForm(@PathVariable (value="id") long id, Model model) {
		Customer cust = this.customerService.getCustomerById(id);
		
		model.addAttribute("customer", cust);
		return "customer_update";		
	}
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable (value="id") long id) {
		this.customerService.deleteCustomerById(id);
		return "redirect:/";
	}
}

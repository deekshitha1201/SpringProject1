package com.example.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entity.BusDetails;
import com.example.Repository.AdminRepository;
import com.example.Repository.BusDetailRepository;


@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private BusDetailRepository busRepository;
	@Override
	public BusDetails addBusByAdmin(BusDetails bus)  {
//		try
//		{
//			AdminInfo admin=adminRepository.findById(id)
//					.orElseThrow(() -> new Exception("Admin with ID " + id + " not found."));
//			BusDetails buses=new BusDetails();
//			buses.setBusName(bus.getBusName());
//			buses.setFromdestination(bus.getFromdestination());
//			buses.setTodestination(bus.getTodestination());
//			buses.setFilterDate(bus.getFilterDate());
//			buses.setTime(bus.getTime());
//			buses.setPrice(bus.getPrice());
//			buses.setAdmininfo(admin);
//			BusDetails savedbusdetails=busRepository.save(buses);
//			return savedbusdetails;
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			throw new Exception("Failed to add buses.");
//		}
	return busRepository.save(bus);
	

	

}
	@Override
	public String removeBus(int id) {
		// TODO Auto-generated method stub
		if(busRepository.existsById(id))
	    {
		 busRepository.deleteById(id);
		}
		return "successfully deleted bus";
		
	}
}

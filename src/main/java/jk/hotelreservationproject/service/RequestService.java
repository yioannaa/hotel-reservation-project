package jk.hotelreservationproject.service;

import jk.hotelreservationproject.model.Request;
import jk.hotelreservationproject.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void saveRequest(Request request){
        requestRepository.save(request);
    }

}

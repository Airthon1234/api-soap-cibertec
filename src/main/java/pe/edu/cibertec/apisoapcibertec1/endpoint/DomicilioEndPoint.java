package pe.edu.cibertec.apisoapcibertec1.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pe.edu.cibertec.apisoapcibertec1.converter.DomicilioConvert;
import pe.edu.cibertec.apisoapcibertec1.repository.DomicilioRepository;
import pe.edu.cibertec.ws.objects.Domiciliows;
import pe.edu.cibertec.ws.objects.GetDomicilioResponse;
import pe.edu.cibertec.ws.objects.GetDomiciliosRequest;
import pe.edu.cibertec.ws.objects.GetDomiciliosResponse;

import java.util.List;

@Endpoint
public class DomicilioEndPoint {

    private static final String NAMESPACE_URI = "";

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private DomicilioConvert domicilioConvert;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDomiciliosRequest")
    @ResponsePayload
    public GetDomiciliosResponse getDomicilios(@RequestPayload GetDomiciliosRequest request){
        GetDomiciliosResponse response = new GetDomiciliosResponse();
        List<Domiciliows> domiciliowsList = domicilioConvert.convertDomicilioToDomicilioWs(domicilioRepository.findAll());
        response.getDomicilios().addAll(domiciliowsList);
        return response;
    }
}

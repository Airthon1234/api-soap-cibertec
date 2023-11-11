package pe.edu.cibertec.apisoapcibertec1.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pe.edu.cibertec.apisoapcibertec1.converter.DomicilioConvert;
import pe.edu.cibertec.apisoapcibertec1.model.Domicilio;
import pe.edu.cibertec.apisoapcibertec1.repository.DomicilioRepository;
import pe.edu.cibertec.ws.objects.*;

import java.util.List;

@Endpoint
public class DomicilioEndPoint {

    private static final String NAMESPACE_URI = "http://www.cibertec.edu.pe/ws/objects";

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private DomicilioConvert domicilioConvert;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDomiciliosRequest")
    @ResponsePayload
    public GetDomiciliosResponse getDomicilios(@RequestPayload
                                               GetDomiciliosRequest request){
        GetDomiciliosResponse response = new GetDomiciliosResponse();
        List<Domiciliows> domiciliowsList = domicilioConvert
                .convertDomicilioToDomicilioWs(domicilioRepository.findAll());
        response.getDomicilios().addAll(domiciliowsList);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDomicilioRequest")
    @ResponsePayload
    public GetDomicilioResponse getDomiciliosXId(@RequestPayload
                                                 GetDomicilioRequest request){
        GetDomicilioResponse response = new GetDomicilioResponse();
        Domiciliows domiciliows = domicilioConvert
                .convertDomicilioToDomicilioWs(
                        domicilioRepository.findById(request.getId()).get());
        response.setDomicilio(domiciliows);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postDomicilioResponse")
    @ResponsePayload
    public PostDomicilioResponse postDomicilio(@RequestPayload PostDomicilioRequest request){
        PostDomicilioResponse response=new PostDomicilioResponse();
        Domicilio newDomicilio = domicilioConvert.convertDomicilioWsToDomicilio(request.getDomicilio());
        Domiciliows newDomiciliows = domicilioConvert.convertDomicilioToDomicilioWs(
                domicilioRepository.save(newDomicilio)
        );
        response.setDomicilio(newDomiciliows);
        return response;
    }


}

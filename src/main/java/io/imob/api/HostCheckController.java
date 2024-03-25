package io.imob.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HostCheckController {

    @GetMapping("/health-check")
    public String checkHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress() + " - " + InetAddress.getLocalHost().getHostName();
    }

}
package com.foursales.foursale_desafio.domain.config;

import com.foursales.foursale_desafio.domain.core.utils.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuditoriaAwareImpl implements AuditorAware<String> {

    @Value("${spring.application.name}")
    private String apiName;

    public Optional<String> getCurrentAuditor() {
        return Objects.nonNull(SecurityContextUtils.getUsername()) ?
                Optional.of(SecurityContextUtils.getUsername()) :
                Optional.of(apiName);
    }
}

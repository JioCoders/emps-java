package com.springboot.empc.servlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class SetParameterRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String[]> paramMap;

    public SetParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        paramMap = new HashMap<>(request.getParameterMap());
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(paramMap);
    }

    public void setParameter(String name, String value) {
        paramMap.put(name, new String[] {value});
    }

    // getParameter() and getParameterValues() are the same as SanitizeParametersRequestWrapper
}

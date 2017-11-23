package fortunes

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description "The fortune service API"
  request {
    method 'GET'
    url '/'
  }
  response {
    status 200
    headers {
      contentType(applicationJson())
    }
    body """
{ "fortune": "a random fortune" } 
"""
  }
}

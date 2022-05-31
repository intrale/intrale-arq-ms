package ar.com.intrale.functions;

import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.intrale.BaseFunction;
import ar.com.intrale.FunctionConst;
import ar.com.intrale.FunctionResponseToBase64HttpResponseBuilder;
import ar.com.intrale.exceptions.FunctionException;
import ar.com.intrale.messages.Get${msName}Request;
import ar.com.intrale.messages.Get${msName}Response;
import ar.com.intrale.builders.StringToGet${msName}RequestBuilder;
import io.micronaut.core.util.StringUtils;

#if ($provider == "cognito")
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
#end
#if ($provider == "dynamoDB")
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
#end
#if ($provider == "s3")
import com.amazonaws.services.s3.AmazonS3;
#end

@Singleton
@Named(FunctionConst.READ)
public class Get${msName}Function extends BaseFunction<Get${msName}Request, Get${msName}Response, 
										#if ($provider == "cognito")
										AWSCognitoIdentityProvider,
										#end
										#if ($provider == "dynamoDB")
											AmazonDynamoDB,
										#end
										#if ($provider == "s3")
											AmazonS3,
										#end
										StringToGet${msName}RequestBuilder, FunctionResponseToBase64HttpResponseBuilder> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Get${msName}Function.class);
	
	public Get${msName}Response execute(Get${msName}Request request)	throws FunctionException {
		Get${msName}Response response = new Get${msName}Response();
		
		//TODO: complete with function code
		
		return response;
	}
}

package ar.com.intrale;

#if ($provider == "dynamoDB")
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
#end

#if ($provider == "cognito")
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
#end

#if ($provider == "s3")
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
#end

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;

@Factory
@Requires(property = IntraleFactory.FACTORY, value = IntraleFactory.TRUE, defaultValue = IntraleFactory.TRUE)
public class ${msName}FactoryImpl extends 
	#if ($provider == "dynamoDB")
		IntraleFactory<AmazonDynamoDB>
	#end
	#if ($provider == "cognito")
		IntraleFactory<AWSCognitoIdentityProvider>
	#end
	#if ($provider == "s3")
		IntraleFactory<AmazonS3>
	#end
	
	
{

	#if ($provider == "dynamoDB")
	@Bean @Requires(property = IntraleFactory.PROVIDER, value = IntraleFactory.TRUE, defaultValue = IntraleFactory.TRUE)
	@Override
	public AmazonDynamoDB provider() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(config.getDatabase().getAccess(), config.getDatabase().getSecret());
    	
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
          .withCredentials(new AWSStaticCredentialsProvider(credentials))
          .withRegion(config.getAws().getRegion())
          .build();
         
        return amazonDynamoDB;
	}
	#end
	
	#if ($provider == "cognito")
	@Bean @Requires(property = IntraleFactory.PROVIDER, value = IntraleFactory.TRUE, defaultValue = IntraleFactory.TRUE)
	@Override
	public AWSCognitoIdentityProvider provider() {
	      BasicAWSCredentials credentials = new BasicAWSCredentials(config.getCognito().getAccess(), config.getCognito().getSecret());
	 	 
	       return AWSCognitoIdentityProviderClientBuilder.standard()
	                      .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                      .withRegion(config.getAws().getRegion())
	                             .build();
	}
	#end
	
	#if ($provider == "s3")
	@Bean @Requires(property = IntraleFactory.PROVIDER, value = IntraleFactory.TRUE, defaultValue = IntraleFactory.TRUE)
	@Override
	public AmazonS3 provider() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(config.getS3().getAccess(), config.getS3().getSecret());
    	
		AmazonS3 amazonDynamoDB = AmazonS3ClientBuilder.standard()
          .withCredentials(new AWSStaticCredentialsProvider(credentials))
          .withRegion(config.getAws().getRegion())
          .build();
         
        return amazonDynamoDB;
	}
	#end
}

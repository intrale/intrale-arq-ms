package ar.com.intrale.messages;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Get${msName.substring(0,1).toUpperCase()}${msName.substring(1)}Request extends RequestRoot {

}

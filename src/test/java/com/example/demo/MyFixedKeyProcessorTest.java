package com.example.demo;

import org.apache.kafka.streams.processor.api.*;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class MyFixedKeyProcessorTest {

  @Test
  void test(){
    var p = new MyFixedKeyProcessor();
    p.init((FixedKeyProcessorContext<String, Integer>) new MockProcessorContext());
    /*
    This fails at runtime with below stack trace

    java.lang.ClassCastException: class org.apache.kafka.streams.processor.api.MockProcessorContext cannot be cast to class org.apache.kafka.streams.processor.api.FixedKeyProcessorContext (org.apache.kafka.streams.processor.api.MockProcessorContext and org.apache.kafka.streams.processor.api.FixedKeyProcessorContext are in unnamed module of loader 'app')

	at com.example.demo.MyFixedKeyProcessorTest.test(MyFixedKeyProcessorTest.java:14)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
	... rest is org.junit stuff
     */
  }

  @Test
  void test2(){
    var p = new MyFixedKeyProcessor();
    p.process(InternalFixedKeyRecordFactory.create(new TestRecord("asdf","asdf")));
    /*
    Required type: Record<KIn,VIn>
    Provided: TestRecord
    reason: no instance(s) of type variable(s) KIn, VIn exist so that TestRecord conforms to Record<KIn, VIn>
     */
  }

  @Test
  void test3(){
    var p = new MyFixedKeyProcessor();
    p.process(new FixedKeyRecord());
    /*
    'FixedKeyRecord(java.lang.Object, java.lang.Object, long, org.apache.kafka.common.header.Headers)' is not public in
    'org.apache.kafka.streams.processor.api.FixedKeyRecord'. Cannot be accessed from outside package
     */
  }
  @Test
  void test4(){
    var p = new MyFixedKeyProcessor();
    p.process(InternalFixedKeyRecordFactory.create(
      new Record<>("key", "value", Instant.now().getEpochSecond()))
    );
    /*
    this works but fails on null pointer of context.forward
     */
  }
}
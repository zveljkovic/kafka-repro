package com.example.demo;

import org.apache.kafka.streams.processor.api.FixedKeyProcessor;
import org.apache.kafka.streams.processor.api.FixedKeyProcessorContext;
import org.apache.kafka.streams.processor.api.FixedKeyRecord;

public class MyFixedKeyProcessor implements FixedKeyProcessor<String, String, Integer> {
  private FixedKeyProcessorContext<String, Integer> context;

  @Override
  public void init(FixedKeyProcessorContext<String, Integer> context) {
    this.context = context;
  }

  @Override
  public void process(FixedKeyRecord<String, String> fixedKeyRecord) {
    context.forward(fixedKeyRecord.withValue(fixedKeyRecord.value().length()));
  }
}

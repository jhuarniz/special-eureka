package br.com.azure.test.bus.queue;

import com.microsoft.azure.servicebus.ClientFactory;
import com.microsoft.azure.servicebus.IMessageSender;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

/**
 * Created by vinicius.yamauchi on 9/12/2017.
 */
public class AzureQueueBusSimple {

    private static final String connectionString = "Endpoint=sb://viveredemobus.servicebus.windows.net/;SharedAccessKeyName=InAppSharedKey;SharedAccessKey=SfpebPMouGKjpD/97s/MuFbSoJ7drLW60i0c7F+3Ivw=";
    private static final String queueName = "inprocessqueue";
    private static final String namespaceName = "viveredemobus";
    private static final String sasKeyName = "RootManageSharedAccessKey";
    private static final String sasKey = "rqzRFpojXjesNrtF+57b5RY5gIAiCbXYsBdl7iHRk4E=";
    private static IMessageSender messageSender;

    public static void main(String[] args) {
        try {
            messageSender = ClientFactory.createMessageSenderFromConnectionStringBuilder(new ConnectionStringBuilder(connectionString, queueName));

            messageSender.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ServiceBusException e) {
            e.printStackTrace();
        }
    }

}

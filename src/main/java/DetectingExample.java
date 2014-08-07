/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DetectingExample {
    public static void main(String[] args) {
        try {
            File file = new File("LICENSE");

            AutoDetectParser parser = new AutoDetectParser();
            InputStream stream = new FileInputStream(file);
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext parseContext = new ParseContext();

            parser.parse(stream, handler, metadata, parseContext);

            String content = handler.toString();

            LanguageIdentifier identifier = new LanguageIdentifier(content);
            String language = identifier.getLanguage();

            System.out.println("The LICENSE is in " + language + ".");
        } catch (IOException e) {
            System.err.println("IO Error while parsing.");
        } catch (TikaException e) {
            System.err.println("Tika error while parsing.");
        } catch (SAXException e) {
            System.err.println("SAX error while parsing.");
        }
    }
}

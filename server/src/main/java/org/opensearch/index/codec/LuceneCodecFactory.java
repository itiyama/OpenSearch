/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.index.codec;

import org.apache.logging.log4j.Logger;
import org.apache.lucene.backward_codecs.lucene92.Lucene92Codec;
import org.apache.lucene.codecs.Codec;
import org.apache.lucene.codecs.lucene95.Lucene95Codec;
import org.opensearch.common.lucene.Lucene;
import org.opensearch.index.mapper.MapperService;


public class LuceneCodecFactory {


    private final String codec;

    public LuceneCodecFactory(String codec) {
        this.codec = codec;
    }

    public Codec getDefaultCodec() {

        if(codec == "Lucene92") {
            return new Lucene92Codec();
        } else {
            return Codec.forName(Lucene.LATEST_CODEC);
        }
    }

    public Codec getBestCompressionCodec() {

        if(codec == "Lucene92") {
            return new Lucene92Codec(Lucene92Codec.Mode.BEST_COMPRESSION);
        } else {
            return new Lucene95Codec(Lucene95Codec.Mode.BEST_COMPRESSION);
        }
    }

    public Codec getBestSpeedCodec() {

        if(codec == "Lucene92") {
            return new Lucene92Codec(Lucene92Codec.Mode.BEST_SPEED);
        } else {
            return new Lucene95Codec(Lucene95Codec.Mode.BEST_SPEED);
        }
    }

    public Codec getPerFieldMappingBestCompressionCodec(MapperService mapperService, Logger logger) {

        if (codec == "Lucene92") {
            return new PerFieldMappingPostingFormatCodecLucene92(Lucene92Codec.Mode.BEST_COMPRESSION, mapperService, logger);
        } else {
            return new PerFieldMappingPostingFormatCodec(Lucene95Codec.Mode.BEST_COMPRESSION, mapperService, logger);
        }
    }

    public Codec getPerFieldMappingBestSpeedCodec(MapperService mapperService, Logger logger) {

        if (codec == "Lucene92") {
            return new PerFieldMappingPostingFormatCodecLucene92(Lucene92Codec.Mode.BEST_SPEED, mapperService, logger);
        } else {
            return new PerFieldMappingPostingFormatCodec(Lucene95Codec.Mode.BEST_SPEED, mapperService, logger);
        }
    }

}

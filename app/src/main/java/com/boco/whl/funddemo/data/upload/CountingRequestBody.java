package com.boco.whl.funddemo.data.upload;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by xxx on 2017/9/7.
 */

public class CountingRequestBody extends RequestBody {
    private RequestBody mRequestBody;
    private InnerUploadListener mUploadListener;
    private CountingSink mCountingSink;

    public CountingRequestBody(RequestBody requestBody, InnerUploadListener uploadListener) {
        mRequestBody = requestBody;
        mUploadListener = uploadListener;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        try {
            return mRequestBody.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        BufferedSink bufferedSink;

        mCountingSink = new CountingSink(sink);
        bufferedSink = Okio.buffer(mCountingSink);

        mRequestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    class CountingSink extends ForwardingSink {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            bytesWritten += byteCount;
            mUploadListener.onRequestProgress(bytesWritten, contentLength());
        }
    }
}

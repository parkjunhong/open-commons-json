[2022/04/07]
- Release: 1.8.0-SNAPSHOT
- Tag: 1.7.0
- Dependencies:
  + open.commons.core: 2.0.0-SNAPSHOT

[2022/04/07]
- Release: 1.7.0

[2021/12/03]
- Dependencies
  + open.commons.core: 1.8.0-SNAPSHOT

[2019/04/09]
- Release: 1.6.4
- Rebuild source directory structure.

[2019/03/29]
- Release: 1.6.3.4
- Update
 + open.commons.core updated: 1.6.5

[2018/09/10]
- Release: 1.6.3.3
- Update
 + open.commons.core updated: 1.4.17

[20148/04/30]
- Release: 1.6.3.2
- Update
 + open.commons.core updated: 1.4.16

[2017/12/13]
- Release: 1.6.3.1
- Update
 + open.commons.core updated: 1.4.10

[2017/11/29]
- Release: 1.6.3
- Add
 + open.commons.json.util.JSONUtil.toMapStartWith(String)
 + open.commons.json.util.JSONUtil.toMapStartWithout(String)

[2017/11/03]
- Release: 1.6.2.1
- Add
 + open.commons.json.util.JSONUtil.toMap(JSONObject): 내부 수정. HashMap -&gt; FIFOMap

[2017/10/20]
- Release: 1.6.2
- Add
 + open.commons.json.function.JSONFunction

[2017/09/22]
- Release: 1.6.1.5
- Update
 + open-commons-core updated: 1.4.7

[2017/09/12]
- Release: 1.6.1.4
- Update
 + open-commons-core updated: 1.4.6

[2017/09/11]
- Release: 1.6.1.3
- Update
 + open-commons-core updated: 1.4.5

[2017/08/21]
- Release: 1.6.1.2
- Update
 + open-commons-core updated: 1.4.2.1 -&gt; 1.4.3.1

[2017/07/06]
- Release: 1.6.1.1
- Update
 + open-commons-core updated: 1.4.2 -&gt; 1.4.2.1

[2016/11/01]
- Release: 1.6.1
- Update
 + open.commons.json.model.toOrderedJSONObject(JSONObject): 버그 수정

[2016/09/21]
- Release: 1.6.0.1
- Update
 + open-commons-core updated: 1.4.0 -&gt; 1.4.1 'properties ordering'

[2016/05/26]
- Release: 1.6.0
- Update
 + set 'properties ordering'

[2016/05/04]
- Release: 1.5.0
- Update
 + code of &quot;org.codehaus.jettison.json&quot;: 1.3.7

[2015/12/23]
- Release: 1.4.7
- Update
 + open-commons-core version: 1.4.0

[2015/12/23]
- Release: 1.4.5.3
- Update
 + open-commons-core version: 1.3.3

[2015/07/23]
- Update
 + open-commons-core version: 1.3.2

[2015/03/17]
- Release: 1.4.5.2
- Update
 + open-commons-core version: 1.3.0

[2015/03/12]
- Release: 1.4.5
- Update
 + open-commons-core version: 1.2.6

[2015/01/28]
- Release: 1.4.4

[2015/01/13]
- Release: 1.4.3.1
- Update
 + open-commons-core version: 1.2.2 &lt;- 1.2.1

[2015/01/06]
- Update
 + open-commons-core version: 1.2.1 &lt;- 1.2.0

[2014/12/29]
- Release
 + 1.4.3
- Add
 + JSONArrayFactory.toJSONArray(Collection&lt;T&gt;, Class&lt;T&gt;, GenericTypeVariable...)
 + JSONArrayFactory.toJSONArray(Collection&lt;T&gt;, Class&lt;T&gt;, List&lt;Class&lt;?&gt;&gt;)

 + JSONArrayFactory.toJSONArray(Collection&lt;T&gt;, Class&lt;T&gt;, Class&lt;?&gt;)

 + JSONArrayFactory.toJSONArray(Object, Class&lt;T&gt;, GenericTypeVariable...)

 + JSONArrayFactory.toJSONArray(Object, Class&lt;T&gt;, List&lt;Class&lt;?&gt;&gt;)

 + JSONArrayFactory.toJSONArray(Object, Class&lt;T&gt;, Class&lt;?&gt;)



[2014/11/25]
- Release
 + 1.4.1 &lt;- 1.3.1
- Update
 + Remove open.commons.json.model.AbstractJSONModel.hashCode()
- Bug Fix
 + Rebirth open.commons.json.model.AbstractJSONModel.hashCode()

[2014/11/11]
- Release
 + 1.3.1 &lt;- 1.3.0.1

[2014/11/07]
- Release
 + 1.3.0.1 &lt;- 1.3.0
 + open-commons-core version: 1.2.0 &lt;- 1.1.3

[2014/11/04]
- Release
 + 1.3.0 &lt;- 1.2.2.1
- Bug Fix
 + open.commons.json.model.JSONMapper.unmarshall(String, Object, BuildConfig, Object ...)에서 
    Object가 null 인 경우 처리

[2014/10/17]
- Release
 + 1.2.2.1 &lt;- 1.2.2
 + open-commons-core version: 1.1.3 &lt;- 1.1.2

[2014/09/26]
 + open-commons-core version: 1.1.2 &lt;- 1.1.1

[2014/09/17]
 + open-commons-core version: 1.1.1 &lt;- 1.1.0

[2014/09/10]
- Release: 1.2.2
- Change
 + open-commons-core version: 1.0.10 -&gt; 1.1.0

[2014/07/29]
- Release: 1.2.1
- Add
 + open.commons.jsonx.util

[2014/07/25]
- Add
 + public static &lt;T&gt; T open.commons.json.util.JSONUtil.get(JSONObject, String, T) throws JSONException;

[2014/07/10]
- Change
 + update open-commons-core version: 1.0.9 -&gt; 1.0.10

[2014/07/07]
- Release 1.0.10
- Patch
 + public static final &lt;T extends Serializable &amp; IJSONString&gt; JSONArray open.commons.json.model.JSONArrayFactory.toJSONArray(Collection&lt;T&gt; values, Class&lt;T&gt; elemType) throws JSONException {
        return toJSONArray(values.toArray(), elemType);
    } 
    의 구현 부분을 
     return toJSONArray(values.toArray(new IJSONString[]{}), elemType);로 수정
[2014/06/19]
- New
 + open.commons.json.model.exception.JSONMarshalledException
 + open.commons.json.model.exception.JSONUnmarshalledException

[2014/06/18]
- Release 1.0.9
 + Support variable json properties.

[2014/06/04]
- Release 1.0.8

- Changes !!!!!!!!!!!!!
 + open.commons.core-1.0.8에 따른 side effects 처리

[2014/06/03]
- New !!!
 + Support classes contains fields which are not a child of IJSONString.

[2014/05/30]
- Hot !!!
 + License is changed!!!
   - before: COPYLEFT by 'Open Commons' &amp;  Park Jun-Hong All Rights Reserved when use for commercial purpose.
   - after: 

[2014/05/08]
- Changed !!!
 +  open.commons.json.model.DefaultJSONModel.investigateField(Field, String) 를 open.commons.json.model.JSONMapper.investigateFiled(T, Field, String)로 이관.
- open.commons.json.model.JSONArrayFactory 에 JSONArray 생성 메소드 추가

[2014/05/07]
- open.commons.json.model.JSONArrayFactory
 + JSONArray 대신 String 를 파라미터로 받는 메소드들 추가.
 
[2014/05/02]
- open-commons-core version up: 1.0.5 -&gt; 1.0.6

# ....
 
1.5
- open.commons.json.model.JSONArrayFactory 추가.

1.4
 - Set a modifier of AbstractJSONModel.hashCode() as 'final'.
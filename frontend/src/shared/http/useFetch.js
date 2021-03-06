import { useEffect, useState } from "react";

/**
 * 
 * @param {string} path 
 */
export function useFetch(path, method = 'GET',fetchOnMount = true, isJson = true) {
    const [isLoading, setLoading] = useState(fetchOnMount);
    const [data, setData] = useState({});
    const [status, setStatus] = useState(null);
    
    const doFetch = async (body = undefined) => {
        let reqInit = { method };
        if (body) {
            reqInit.body = JSON.stringify(body);
            reqInit.headers = { 'Content-Type': 'application/json' };
        }
        setLoading(true);
        const response = await fetch(path, reqInit);
        setLoading(false);
        setStatus(response.status);
        if (isJson) setData(await response.json());
        return response;
    }

    useEffect(() => {
        if (fetchOnMount) {
            const fetchEffect = async () => {
                const response = await fetch(path);
                const data = await response.json();
                setStatus(response.status);
                setData(data);
                setLoading(false);
            }
            fetchEffect();
        }
    }, [path, fetchOnMount])

    return { isLoading, data, status, doFetch };
}
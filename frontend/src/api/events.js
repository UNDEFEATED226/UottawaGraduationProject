
// --------------------------- GET ---------------------------

export const getAllEvents = async () => {
    const response = await fetch(`/api/main_events/find_all`);
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getEventAndRelations = async (id) => {
    const data = await Promise.all([
        getEvent(id),
        getPastEvents(id),
        getFutureEvents(id),
        getEventGrants(id),
        getEventMembers(id),
        getEventProducts(id),
        getEventPartners(id),
        getEventTopics(id),
    ]);
    if (data.includes(null)) return null;
    return {
        event: data[0],
        pastEvents: data[1],
        futureEvents: data[2],
        eventGrants: data[3],
        eventMembers: data[4],
        eventProducts: data[5],
        eventPartners: data[6],
        eventTopics: data[7],
    };
}

export const getEvent = async (id) => {
    const response = await fetch(`/api/main_events/find_by_id?id=${id}`);
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getPastEvents = async (id) => {
    const response = await fetch(`/api/relp_event_event/find_all_by_future_event_id?futureEventId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.pastEventId);
    }
    return null;
}

export const getFutureEvents = async (id) => {
    const response = await fetch(`/api/relp_event_event/find_all_by_past_event_id?pastEventId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.futureEventId);
    }
    return null;
}

export const getEventGrants = async (id) => {
    const response = await fetch(`/api/relp_event_grant/find_all_by_event_id?eventId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.grantId);
    }
    return null;
}

export const getEventMembers = async (id) => {
    const response = await fetch(`/api/relp_event_member/find_all_by_event_id?eventId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.memberId);
    }
    return null;
}

export const getEventProducts = async (id) => {
    const response = await fetch(`/api/relp_event_product/find_all_by_event_id?eventId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.productId);
    }
    return null;
}

export const getEventPartners = async (id) => {
    const response = await fetch(`/api/relp_event_partner/find_all_by_event_id?eventId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.partnerId);
    }
    return null;
}

export const getEventTopics = async (id) => {
    const response = await fetch(`/api/relp_event_topic/find_all_by_event_id?eventId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.themeId);
    }
    return null;
}

// -------------------------- UPDATE --------------------------

export const updateEvent = async (event) => {
    const response = await fetch('/api/main_events/update', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(event)
    });
    return response.ok;
}

// -------------------- UPDATE PAST EVENTS --------------------

export const updatePastEvents = async (eventId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deletePastEvent(eventId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addPastEvent(eventId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deletePastEvent = async (eventId, pastEventId) => {
    const response = await fetch(`/api/relp_event_event/delete_by_id?pastEventId=${pastEventId}&futureEventId=${eventId}`);
    return response.ok;
}

export const addPastEvent = async (eventId, pastEventId) => {
    let newRel = {id: {pastEventId: pastEventId, futureEventId: eventId}};
    const response = await fetch('/api/relp_event_event/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// -------------------- UPDATE FUTURE EVENTS --------------------

export const updateFutureEvents = async (eventId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteFutureEvent(eventId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addFutureEvent(eventId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteFutureEvent = async (eventId, futureEventId) => {
    const response = await fetch(`/api/relp_event_event/delete_by_id?pastEventId=${eventId}&futureEventId=${futureEventId}`);
    return response.ok;
}

export const addFutureEvent = async (eventId, futureEventId) => {
    let newRel = {id: {pastEventId: eventId, futureEventId: futureEventId}};
    const response = await fetch('/api/relp_event_event/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ----------------------- UPDATE EVENT GRANTS -----------------------

export const updateEventGrants = async (eventId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteEventGrant(eventId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addEventGrant(eventId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteEventGrant = async (eventId, grantId) => {
    const response = await fetch(`/api/relp_event_grant/delete_by_id?eventId=${eventId}&grantId=${grantId}`);
    return response.ok;
}

export const addEventGrant = async (eventId, grantId) => {
    let newRel = {id: {eventId: eventId, grantId: grantId}};
    const response = await fetch('/api/relp_event_grant/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ----------------------- UPDATE EVENT MEMBERS -----------------------

export const updateEventMembers = async (eventId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteEventMember(eventId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addEventMember(eventId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteEventMember = async (eventId, memberId) => {
    const response = await fetch(`/api/relp_event_member/delete_by_id?eventId=${eventId}&memberId=${memberId}`);
    return response.ok;
}

export const addEventMember = async (eventId, memberId) => {
    let newRel = {id: {eventId: eventId, memberId: memberId}};
    const response = await fetch('/api/relp_event_member/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ----------------------- UPDATE EVENT PRODUCTS -----------------------

export const updateEventProducts = async (eventId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteEventProduct(eventId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addEventProduct(eventId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteEventProduct = async (eventId, productId) => {
    const response = await fetch(`/api/relp_event_product/delete_by_id?eventId=${eventId}&productId=${productId}`);
    return response.ok;
}

export const addEventProduct = async (eventId, productId) => {
    let newRel = {id: {eventId: eventId, productId: productId}};
    const response = await fetch('/api/relp_event_product/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ----------------------- UPDATE EVENT PARTNERS -----------------------

export const updateEventPartners = async (eventId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteEventPartner(eventId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addEventPartner(eventId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteEventPartner = async (eventId, partnerId) => {
    const response = await fetch(`/api/relp_event_partner/delete_by_id?eventId=${eventId}&partnerId=${partnerId}`);
    return response.ok;
}

export const addEventPartner = async (eventId, partnerId) => {
    let newRel = {id: {eventId: eventId, partnerId: partnerId}};
    const response = await fetch('/api/relp_event_partner/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ----------------------- UPDATE EVENT TOPICS -----------------------

export const updateEventTopics = async (eventId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteEventTopic(eventId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addEventTopic(eventId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteEventTopic = async (eventId, themeId) => {
    const response = await fetch(`/api/relp_event_topic/delete_by_id?eventId=${eventId}&themeId=${themeId}`);
    return response.ok;
}

export const addEventTopic = async (eventId, themeId) => {
    let newRel = {id: {eventId: eventId, themeId: themeId}};
    const response = await fetch('/api/relp_event_topic/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}
